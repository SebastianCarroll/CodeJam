require 'set'

class Cell
	attr_accessor :x, :y

	def initialize(x, y)
		@x = x
		@y = y
		@str = "#{@x} #{@y}"
	end

	def finished?
		(@x == 0 && @y == 0) ||
			(@x == -1 && @y == -1)
	end

	# moves the square three on the x axis
	def shift
		@x += 3
		@str = "#{@x} #{@y}"
	end

	def to_s
		@str
	end

	def eql?(other)
		@x == other.x && @y == other.y
	end

	def hash
		(@x + (@y *100)).hash
	end

	def self.from_str(str)
		x,y = str.strip.split(" ")
		Cell.new(x.to_i, y.to_i)
	end

end

class Grid
	def initialize
		@grid = Array.new(3){Array.new(3, false)}
		@size = 0
	end

	def dug(hole)	
		x = hole.x % 3
		y = hole.y % 3
		STDERR.puts @grid
		r = @grid[x][y]
		unless(r)
			@grid[x][y] = true
			@size += 1
		end
	end

	def complete?
		@size == 9
	end

	def to_s
		@grid.to_a.join ", "
	end
end

def loop_with_prog
	a = gets.strip.to_i
	STDERR.puts "Read a:#{a}"
	grid = Grid.new
	dig = Cell.new(10,10)
	1001.times do |try|
		puts dig
		STDOUT.flush
		dug = Cell.from_str(gets)
		if dug.finished? 
			STDERR.puts "Finished? Sent: #{dig}. Got: #{dug}. Try: #{try}. Grid: #{grid}"
			break
		end
		grid.dug(dug)
		dig.shift if( grid.complete? )
	end
end

if __FILE__ == $0
	#STDOUT.sync = true
	t = gets.strip.to_i
	STDERR.puts "Read t:#{t}"
	t.times do |i|
		loop_with_prog()
	end
end
