package se.skillytaire.belastingdienst.ee.annotation;

import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("se.skillytaire.belastingdienst.ee.annotation.Builder")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class BuilderProcessor extends AbstractProcessor {
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (TypeElement annotation : annotations) {
			for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
				if (element.getKind() != ElementKind.CLASS)
					continue;
				List<Element> enclosedElements = element.getEnclosedElements().stream()
						.filter((e) -> e.getKind() == ElementKind.FIELD).collect(toList());
				processFields(element, enclosedElements);
			}
		}
		return false;
	}

	private void processFields(Element enclosing, List<Element> fields) {
		try {
			String packageName = processingEnv.getElementUtils().getPackageOf(enclosing).toString();
			String className = enclosing.getSimpleName() + "Builder";
			JavaFileObject srcFile = processingEnv.getFiler().createSourceFile(packageName + "." + className);
			try(Writer writer = srcFile.openWriter()) {
				writer.write("package " + packageName + ";");
				writer.write("\n\npublic class " + enclosing.getSimpleName() + "Builder {");
				writer.write(String.format("\n\tprivate %s build = new %s();\n", enclosing.getSimpleName(), enclosing.getSimpleName()));

				for (Element field : fields) {
					if(field.getAnnotation(BuilderField.class) == null) continue;
					writer.write(String.format("\n\tpublic %s set%s(%s %s) {", className, field.getSimpleName().toString().substring(0, 1).toUpperCase() + field.getSimpleName().toString().substring(1), field.asType().toString(), field.getSimpleName()));
					writer.write(String.format("\n\t\tbuild.set%s(%s);", field.getSimpleName().toString().substring(0, 1).toUpperCase() + field.getSimpleName().toString().substring(1), field.getSimpleName()));
					writer.write("\n\t\treturn this;");
					writer.write("\n\t}");
					// System.out.println(String.format("\t%s:%s -> %s", field.getSimpleName(), field.getKind(), field.asType().toString()));
				}
				writer.write(String.format("\n\tpublic %s build() {", enclosing.getSimpleName()));
				writer.write(String.format("\n\t\treturn this.build;"));
				writer.write("\n\t}");
				writer.write("\n}");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}