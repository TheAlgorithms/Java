def can_reach_last_index_optimized(nums):
  """
  Determines whether the last index of an integer array can be reached by jumping from the first index,
  where each element in the array represents the maximum jump length at that position.

  Args:
    nums: A list of integers.

  Returns:
    A boolean value, True if the last index can be reached, False otherwise.
  """

  # Initialize the current index and maximum reachable index.
  current_index = 0
  max_reachable_index = nums[0]

  # Loop until we reach the last index or we cannot jump any further.
  while current_index < len(nums) - 1:
    # If the maximum reachable index is not enough to reach the last index, return false.
    if max_reachable_index < len(nums) - 1:
      return False

    # Update the current index and maximum reachable index.
    current_index += 1
    max_reachable_index = max(max_reachable_index, nums[current_index] + current_index)

  # If we reach this point, we can reach the last index. Return true.
  return True
