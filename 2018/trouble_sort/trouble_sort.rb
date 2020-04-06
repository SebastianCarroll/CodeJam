class Trouble
	def sortable?(list)
		sorted = sort(list)
		check(sorted)
	end

	def sort(list)
		done = false
		until done do
			done = true
			max = list.length - 2
			(0...max).each do |i|
				if(list[i] > list[i+2])
					done = false
					tmp = list[i]
					list[i] = list[i+2]
					list[i+2] = tmp
				end
				# Can probably short circuit on this condition
				#if( i==0 && list[i] > list[i+1] )
			end
		end
		list
	end

	def check(list)
		ret = "OK"
		list.each_cons(2).each_with_index do |a,i|
			if( a.first > a.last )
				ret = i
				break
			end
		end
		ret
	end
				
end

if __FILE__ == $0
	t = gets.strip.to_i
	t.times do |i|
		n = gets.strip.to_i
		v = gets.strip.split.map(&:to_i)
		puts "Case \##{i+1}: #{Trouble.new.sortable? v}"
	end
end
