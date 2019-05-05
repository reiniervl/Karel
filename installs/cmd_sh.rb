fin = File.open('install.cmd')
fout = File.open('install.sh', 'w')

fin.each_line { |line|
	fout.write((line.gsub(/^call/, '')).strip() + "\n")
}
