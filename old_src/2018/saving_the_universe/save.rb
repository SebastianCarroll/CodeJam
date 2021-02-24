class President
	def min_hacks_to_save(program, shield)
		p = Program.new(program)
		if( p.damage <= shield )
			0
		else
			begin
				loop do
					p.switch_last
					break if (p.damage <= shield)
				end
			rescue TypeError
				"IMPOSSIBLE"
			else
				p.hacks
			end
		end
	end
end

class Program
	def initialize(input)
		@actions = input
		@switches = 0
	end
	
	def damage
		total = 0
		power = 1
		@actions.each_char do |c|
			if( c == 'C' )
				power *= 2
			elsif( c == 'S' )
				total += power
			end
		end
		total
	end

	def switch_last
		# find the last occurance of CS
		# This can be swapped to SC for the greatest reduction in damage
		last = @actions.rindex("CS")
		@actions[last, 2] = "SC"
		@switches += 1
	end

	def hacks
		@switches
	end
end

if __FILE__ == $0
	t = gets.strip.to_i
	t.times do |i|
		shield_str, program = gets.split(' ')
		min_hacks = President.new.min_hacks_to_save(program, shield_str.to_i)
		puts "Case \##{i+1}: #{min_hacks}"
	end
end
