package set;



public class RemoveDuplicates  extends MaxSet<Integer> {

	public static int[] uniqueElements(int[] ints){
		MaxSet<Integer> uniqueList = new MaxSet<Integer>();
		if(ints == null){
			return null;
		}
		 for(int i=0;i<ints.length;i++){
			 uniqueList.add(ints[i]);	 
		 }
		 int[] sortedList = new int[uniqueList.size()];
		 for(int i = uniqueList.size()-1; i>=0; i--){
			 sortedList[i] = uniqueList.getMax();
			 uniqueList.remove(uniqueList.getMax());
		 }
		
		return sortedList;
	}
	
	
	
	
	
	public static void main(String[] args){
		int[] ints = {7, 5, 3, 5, 2, 2, 7};
		
		for(int i:uniqueElements(ints)){
			System.out.print(i);
		}
	}
	
		
	
	
	
	
	

}
