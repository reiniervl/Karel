if File.exists?('install.cmd')
	fin = File.open('install.cmd')
	fout = File.open('install.sh', 'w')

	puts "[\e[32m\e[1mRUBY\e[0m] Writing lines"
	fin.each_line { |l|
		line = (l.gsub(/^call/, '')).strip() + "\n"
		fout.write(line)
		if line.length > 1
			puts "[\e[36m\e[1mINFO\e[0m] #{line}"
		end
	}
end