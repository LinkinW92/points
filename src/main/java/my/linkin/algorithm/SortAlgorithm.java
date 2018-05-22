package my.linkin.algorithm;

/***
 * @author linkin
 * @desc 排序算法的简单实现， 递增排序
 * */
public class SortAlgorithm {
	
	
	/***************************插入排序**********************************/
	/**
	 * 简单插入, a[0]为起始点
	 * */
	public static void insertSort(int[] a) {
		for(int i=1; i<a.length; i++) {
			int tmp = a[i];//保存待插入的值
			int j;
			for(j = 0; j<i && a[i] > a[j];j++);//找到插入的位置
			for(int k=i; k>j;k--) {
				a[k] = a[k-1];
			}
			a[j] = tmp;
		}
		
		for(int i:a) {
			System.out.print(i + ",");
		}
		System.out.println();
		
	}
	
	/**
	 * 希尔, 初始步长k为4
	 * */
	public static void shellSort(int[] a) {
		for(int k = 4; k >= 1; k--) {
			for(int i=0;i<k; i++) {
				int len;
				if(i < a.length%k) {
					len = a.length/k + 1;
				}else {
					len = a.length/k;
				}
				int[] b = new int[len];
				for(int j=0; j<=a.length/k;j++) {
					if(j*k+i < a.length)
					b[j] = a[j*k + i];
				}
				insertSort(b);
				for(int j=0; j<=a.length/k;j++) {
					if(j*k+i < a.length)
					a[j*k + i] = b[j];
				}
			}
			for(int e : a) {
				System.out.print(e + ",");
			}
			System.out.println();
		}
		
		for(int e : a) {
			System.out.print(e + ",");
		}
		System.out.println();
		
	}
	
	
	/***********************交换排序*********************************/
	/**
	 * 快速, 以a[0]为pivot, 递增排序
	 * */
	public static void quickSort(int[] a, int left, int right) {
		
		int l = left, r = right;
		int pivot = left;
		while(left < right-1) {
			while(a[right] > a[pivot] && right > left)right--;
			int tmp;
			if(a[right] < a[pivot]) {
				tmp = a[right];
				a[right] = a[pivot];
				a[pivot] = tmp;
				pivot = right;
				
			}
			
			
			while(a[left] < a[pivot] && left < right) left ++;
			if(a[left] > a[pivot]) {
				tmp = a[left];
				a[left] = a[pivot];
				a[pivot] = tmp;
				pivot = left;				
			}
		
		}
		
		if(l < pivot){
			quickSort(a, l, pivot);
			
		}
		if(pivot + 1 < r){
			quickSort(a, pivot + 1, r);
		}
		
		System.out.println(right + "--" + left + "--" + pivot);
		for(int e : a) {
			System.out.print(e + ",");
		}
		System.out.println();
	}
	
	
	/**
	 * 冒泡
	 * */
	public static void bubbleSort(int[] a) {
		int tmp;
		for(int m = 0; m < a.length; m++) {
			for(int i=0; i<a.length - m; i++) {
				if(i != a.length -m -1 && a[i] > a[i+1]) {
					tmp = a[i];
					a[i] = a[i+1];
					a[i+1] = tmp;
				}
			}
			for(int i : a) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}
	}
	
	/*********************************选择排序********************************************/
	/**
	 * 简单选择排序
	 * */
	static void simpleSelectSort(int[] a) {
		int min = 0;
		for(int m = 0; m < a.length; m++ ){
			min = m;
			for(int n = m; n < a.length; n++) {
				if( a[n] < a[min]) {
					min = n;
				}
			}
			int tmp = a[m];
			a[m] = a[min];	
			a[min] = tmp;
			for(int e : a) {
				System.out.print( e + ",");
			}
			System.out.println();
		}
	}
	/**
	 * 堆排序(大顶堆)
	 * */
	static void heapsort(int[] a) {
		for(int k = 0; k<a.length;k++) {
			//从后往前遍历，调整堆顺序
			for(int i=a.length - k; i>=2;i--) {
				if(a[i-1] > a[i/2 - 1]) {
					int tmp = a[i-1];
					a[i-1] = a[i/2 - 1];
					a[i/2 - 1] = tmp;
				}	
			}
			System.out.println("堆顶元素为：" + a[0]);
			//交换堆顶与最后一个元素的位置
			int tmp = a[0];
			a[0] = a[a.length - 1 - k];
			a[a.length - k - 1] = tmp;
			
			for(int i : a) {
				System.out.print(i + ",");
			}
			System.out.println();
		}
		
		
	}
	
	
	/************************归并排序*******************************************/
	/**
	 * 二路归并排序
	 * */
	
	static void mergeSort(int[] a, int start, int end) {
		if(start + 1 == end && a[start] > a[end]) {
			int tmp = a[start];
			a[start] = a[end];
			a[end] = tmp;
		}
		
		if(start + 1 < end) {
			mergeSort(a, start, (start + end)/2);//左边归并
			mergeSort(a, (start + end)/2 + 1, end);//右边归并
			mergeArray(a, start, (start + end)/2, end);//左右合并
		}
		for(int i: a) {
			System.out.print(i + ",");
		}
		System.out.println();
		
		
		//start=end的情况不处理
	}
	
	/**
	 * 合并左右划分的结果， 用插入排序， 将右边的数插到左边有序的队列中.
	 * */
	static void mergeArray(int[] a, int start, int mid, int end) {
		for(int i = mid + 1; i <= end; i++) {
			for(int j = start; j <= i; j++){//注意， 此处的上限是i
				if(a[i] < a[j]) {
					//保存要插入的元素， 并使插入位置之后的元素后移
					int tmp = a[i];
					for(int m = i-1; m>=j;m--) {
						a[m+1] = a[m];
					}
					a[j] = tmp;
				}
			}
		}
	}
	/**
	 * 多路归并排序
	 * */
	static void multiMerge(int[] a) {
		
	}
	
	
	public static void main(String[] args) {
		int[] a = {3, 1, 5, 9, 2, 4, 1, 0, 21, 8, 11, 101, 99, 201, 33, 44, 43};
		
		for(int e : a) {
			System.out.print(e + ",");
		}
		System.out.println();
		//bubbleSort(a);
		//simpleSelectSort(a);
		//heapsort(a);
		//mergeSort(a, 0, a.length-1);
		//quickSort(a, 0, a.length - 1);
		//insertSort(a);
		shellSort(a);
		int a1 = 1, b1 = 2;
		swapInt(a1, b1);
		System.out.println(a1+"||"+b1);
		String a2 = "128", b2= "128";
		swapString(a2, b2);
		System.out.println(a2+"||" + b2);
		System.out.println(a2==b2);
		
		
	
	}
	
	static void swapInt(int a, int b) {
		int tmp = a;
		a = b;
		b = tmp;
	}
	
	static void swapString(String a, String b) {
		String tmp = a;
		a = b;
		b = tmp;
		
	}

}
