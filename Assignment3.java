//package PA3;

public class Assignment3 {

	int o = 0;										// o refers to OPT value in memoized array
	int s = 1;										// s refers to sum value in memoized array
	int w = 2;										// w refers to wall value in memoized array
	
	
    public int maxFruitCount (int[] sections) {
        // Implement your dynamic programming algorithm here
        // You may use helper functions as needed
    	int size = sections.length;
    	int[][][] mem = new int[size + 1][size + 1][3];	// memoized table to store previous OPT and sums
    	for(int i = 1; i <= size; i++) {
    		mem[i][i][o] = 0;
			mem[i][i][s] = sections[i - 1];
			mem[i][i][w] = 1;
    	}
    	
//    	for(int row = 1; row <= size; row++) {
//    		for(int col = 1; col <= size; col++) {
//    			
//    			// initialize diagonal
//    			
//    			if(row == col) {
//    				mem[row][col][o] = 0;
//    				mem[row][col][s] = sections[row - 1];
//    			}
//    			
////    			//initialize empty values
////    			
////    			else{
////    				mem[row][col][o] = -1;
////    				mem[row][col][s] = -1;
////    			}
//    		}
//    	}
    	
    	// humans conquer all territory
    	
    	if(size <= 1) return 0;
    	
    	// populate array diagonally
    	
    	else {
    		
    		int start = 1;
    		int end = start + 1;
    		int counter = 2;

    		while(start <= size && end <= size) {
    			int subSize = (end - start) + 1;
				int max = -1;
				int sum = -1;
				int last = 0;

				int wall = mem[start][end - 1][w];
				int saveWall = wall;
				
				for(; wall < subSize; wall++) {
					sum = mem[start][start + wall - 1][s] + mem[start + wall][end][s];
					int optLeft = mem[start][start + wall - 1][s] + mem[start][start + wall - 1][o];
					int optRight = mem[start + wall][end][s] + mem[start + wall][end][o];
					int opt = (optLeft < optRight) ? optLeft : optRight;
					if(opt > max) {
						max = opt;
						saveWall = wall;
					}
					if((opt- last) <= 0) {
						wall = subSize;
					}
					last = opt;
				}
				mem[start][end][s] = sum;
				mem[start][end][o] = max;
				mem[start][end][w] = saveWall;
				
				start++;
				end ++;
				if(end > size) {
					if(counter < size) {
					start = 1;
					end = start + counter;
					counter ++;
//					prevWall = 1;
					}
				}
    		}
    		return mem[1][size][o];
    	}
    }
}
    
//    public int maxRec (int[] sections, int[][][] arr, int start, int end, int size) {
//    	if(size <= 1) return 0;
//    	
//    	else {
//    		int max = -1;
//			for (int wall = 1; wall < size; wall++) {
//				int min = Integer.MAX_VALUE;
//				int left = -1;
//				
//				if(arr[wall][end][1] != -1) {
//					left = arr[wall][end][1] + maxRec(sections, arr, wall, end, size - wall);
//				}else{
//					left = approachFromLeft(sections, arr, start, end, size, wall);
//				}
//				
//				int right = approachFromRight(sections, arr, start, end, size, wall);
//				int lesser = (left > right) ? right : left;
//				min = (min < lesser) ? min : lesser;
//				if (min > max)
//					max = min;
//			}
//			return max;
//		}
//	}
//    
//    public int approachFromLeft(int[] sections, int[][][] arr, int start, int end, int size, int wall) {
//    		int vApes = 0;
//    		for(int i = 0; i < (size - wall) ; i++) {
//        		vApes += sections[start + wall + i];
//        	}
//    		arr[wall][end][1] = vApes;
//    		arr[wall][end][0] = vApes + maxRec(sections, arr, wall, end, size - wall);
//    		return arr[wall][end][0];
//    }
//    
//    public int approachFromRight(int[] sections, int[][][] arr, int start, int end, int size, int wall) {
//    	int vApes = sums[start][wall];
////    	for(int i = start; i < (start + wall) ; i++) {
////    		vApes += sections[i];
////    	}
//    	return vApes + maxRec(sections, sums, start, wall, wall - start);
//    }
//    
//    public void calculateSums(int[] sections, int [][][] arr, int start, int end, int size) {
//    	int[][] sums = new int[size][size];
//    	for(int row = 0 ; row < size; row++) {
//    		for(int col = 0; col < size; col++) {
//    			if(row == col) {
//    				sums[row][col] = sections[row];
//    			}
//    		}
//    	}
//    	for(int row = 0 ; row < size; row++) {
//    		for(int col = 0; col < size; col++) {
//    			if(col <= row) continue;
//    			else {
//    				sums[row][col] = sums[row][col-1] + sums[col][col];
//    			}
//    		}
//    	}
//    }
//    
//    public int[] getDifferences (int[] sections, int [][][] arr, int start, int end, int size) {
//    	int[] left = new int[size - 1];
//		int[] right = new int[size - 1];
//		left[0] = sections[start];
//		right[size - 2] = sections[end];
//		for(int i = 1; i < (size - 1); i++) {
//			left[i] = left[i-1] + sections[start+i];
//			right[size - 2 - i] = right[size - 1 - i] + sections[end - i];
//		
//		}
//		int[] diffs = new int[size - 1];
//		for(int i = 0; i < (size - 1); i++) {
//			diffs[i] = left[i] - right[i];
//		}
//    	return diffs;
//    }



