package se.skillytaire.belastingdienst.ee.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("se.skillytaire.belastingdienst.ee.annotation.Builder")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class BuilderProcessor extends AbstractProcessor {

   @Override
   public boolean process(final Set<? extends TypeElement> annotations,
         final RoundEnvironment roundEnv) {
      return false;
   }

}
