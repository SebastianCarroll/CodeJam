require 'minitest/autorun'
require_relative 'trouble_sort.rb'

class TroubleSort < Minitest::Test
	def test_fail
		list = [8, 9, 7]
		assert_equal Trouble.new.sortable?(list), 1
		assert_equal Trouble.new.sortable?([64, 3, 71, 3, 85]), 0
		assert_equal Trouble.new.sortable?([65, 43, 13, 20, 85, 53, 28, 69, 39, 54]), 3
	end

	def test_success
		assert_equal Trouble.new.sortable?([5, 6, 8, 4, 3]), "OK"
		assert_equal Trouble.new.sortable?([5, 6, 6, 4, 3]), "OK"
	end

	def test_random
		10.times do
			input = Array.new(10) { rand(1..100) }
			expected = input.sort
			orig = input.clone
			res = Trouble.new.sortable?(input)
			puts "Failed to sort at index #{res}. Input: #{orig}, Ouput: #{input}"
			assert ((expected == orig && res == "OK") || expected != input)
		end
	end
end
