package com.syl.baisc.chpater2;

/**
 * @ClassName: Example31
 * @Description:
 * @author Bright
 * @date 2017年8月8日 下午10:17:09
 */
public class Example31 {
	public static void main(String[] args) {
		int[] arr = { 4, 6, 3, 9, 8 };
		System.out.println("排序前==");
		printArr(arr);
		getMax(arr);
		System.out.println("arr的最大值是:" + getMax(arr));
		System.out.println("排序后==");
		bubbleSort(arr);
	}

	/**
	 * @Title: bubbleSort
	 * @Description:冒泡排序,最外面要比较arr.length - 1次
	 * @param arr
	 * @return void
	 * @throws
	 */
	private static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		printArr(arr);
	}

	/**
	 * @Title: printArr
	 * @Description:
	 * @param arr
	 * @return void
	 * @throws
	 */
	private static void printArr(int[] arr) {
		System.out.print("打印数组:");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	/**
	 * @Title: getMax
	 * @Description:
	 * @param arr
	 * @return void
	 * @throws
	 */
	private static int getMax(int[] arr) {
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= max) {
				max = arr[i];
			}
		}
		return max;
	}
}
