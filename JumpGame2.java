class JumpGame2 {
	public int jump(int steps[]) {

		int maxReach = 0;

		int n = steps.length;
		int currentEnd = 0;
		int jumps = 0;

		for (int i = 0; i < n - 1; i++) {
			int step = steps[i];

			maxReach = Math.max(maxReach, i + step);

			// this if block not needed, good to have , but its already covered below
			if (maxReach >= n - 1)
				return jumps + 1;

			if (i == currentEnd) {
				jumps++;

				// this if block not needed, only needed if its possible that we will never
				// reach the solution
				if (maxReach <= i)
					return -1;

				currentEnd = maxReach;
			}
		}

		return jumps;

	}
}
