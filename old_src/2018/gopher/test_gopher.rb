require 'minitest/autorun'
require_relative 'gopher.rb'

class TroubleSort < Minitest::Test
	def test_cell_init
		x = 10
		y = 11
		p = Cell.new(x, y)
		assert_equal p.x, x
		assert_equal p.y, y
		p2 = Cell.from_str("10 11")
		assert_equal p2.x, x
		assert_equal p2.y, y

		assert Cell.new(-1,-1).finished?
		assert Cell.new(0,0).finished?
	end

	def test_cell_finished
		assert Cell.new(-1,-1).finished?
		assert Cell.new(0,0).finished?
	end

	def test_cell_to_string
		p = Cell.new(10, 22)
		assert_equal p.to_s, "10 22"
	end

	def test_shift
		x = 10
		y = 11
		p = Cell.new(x, y)
		assert_equal p.x, x
		p.shift
		assert_equal p.x, x+3
	end

	def test_break_command
		max = 36
		cur = 0
		10.times do |i|
			5.times do |j|
				cur = i*j
				if( i==0 && j==0)
					puts "first run"
					# Testing break exits only one level not both
					break 
				end
			end
		end
		assert_equal max, cur
	end
end
