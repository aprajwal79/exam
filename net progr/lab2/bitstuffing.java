class bitstuffing {

	static void bitStuffing(int N, int arr[])
	{

		int[] brr = new int[30];

		int i, j, k;
		i = 0;
		j = 0;

		while (i < N) {

			if (arr[i] == 1) {
				int count = 1;
				brr[j] = arr[i];
				for (k = i + 1; k < N && arr[k] == 1 && count < 5; k++) {
					j++;
					brr[j] = arr[k];
					count++;

					if (count == 5) {
						j++;
						brr[j] = 0;
					}
					i = k;
				}
			}
			else {
				brr[j] = arr[i];
			}
			i++;
			j++;
		}

		for (i = 0; i < j; i++)
			System.out.printf("%d", brr[i]);
	}

	public static void main(String[] args)
	{
		int N = 6;
		int arr[] = { 1, 1, 1, 1, 1, 1 };

		bitStuffing(N, arr);
	}
}
