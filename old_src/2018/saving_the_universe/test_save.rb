require 'minitest/autorun'
require_relative 'save.rb'

class WorldSave < Minitest::Test
	def test_shield_sufficient_without_hacking
		program = "CS"
		shield = 2
		min_hacks = President.new.min_hacks_to_save(program, shield)
		assert_equal min_hacks, 0 
	end

	def test_no_shots_fired
		program = "CCCCC"
		shield = 0
		min_hacks = President.new.min_hacks_to_save(program, shield)
		assert_equal min_hacks, 0 
	end

	def test_single_swap
		program = "CS"
		shield = 1
		min_hacks = President.new.min_hacks_to_save(program, shield)
		assert_equal 1, min_hacks
	end

	def test_fully
		program = "CSCSS"
		shield = 3
		min_hacks = President.new.min_hacks_to_save(program, shield)
		assert_equal 5, min_hacks
	end

	def test_impossible
		program = "SSSCC"
		shield = 2
		min_hacks = President.new.min_hacks_to_save(program, shield)
		assert_equal "IMPOSSIBLE", min_hacks
	end

	def test_empty_prog
		program = ""
		shield = 0
		min_hacks = President.new.min_hacks_to_save(program, shield)
		assert_equal 0, min_hacks
	end

	def test_no_C_chars
		program = "SSSSSS"
		shield = 3
		min_hacks = President.new.min_hacks_to_save(program, shield)
		assert_equal "IMPOSSIBLE", min_hacks
	end
end
