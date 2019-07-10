import java.io.*;

class heapOps {
    
    public static int[] heap;
    public static int capacity = 50, cur_size=0;
    public static final int MAX_HEAP = 1, MIN_HEAP = 0;
    public static int MODE;
    
    
    public static void init(int mode)
    {
        MODE = mode;
        heap = new int[capacity];
    }
    
    public static void showHeap()
    {
        int i = 0;
        for(int step=1;i<cur_size;step*=2)
        {
            for(int j=0;j<step&&i<cur_size;j++)
            {
                display(heap[i]+" ");
                i++;
            }
            display("\n");
        }
    }
    
    public static boolean spaceAvailable(){ return capacity>cur_size; }
    
    public static boolean isEmpty(){ return cur_size==0; }
    
    public static void display(String s)
    {
        System.out.print(s+"");
    }
    
    public static int getParentIndex(int index){ return (index-1)/2; };
    public static int getParentValue(int index){ return heap[getParentIndex(index)]; };
    
    public static int getLeftChildIndex(int index){ return 2*index+1; };
    public static int getLeftChildValue(int index){ return heap[getLeftChildIndex(index)]; }
    public static boolean hasLeftChild(int index){ return getLeftChildIndex(index) <cur_size; }
    
    public static int getRightChildIndex(int index){ return 2*index+2; };
    public static int getRightChildValue(int index){ return heap[getRightChildIndex(index)]; }
    public static boolean hasRightChild(int index){ return getRightChildIndex(index) <cur_size; }
    
    public static void swap(int index1, int index2)
    {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
    
    public static void insert(int value)
    {
        if(spaceAvailable())
        {
            heap[cur_size] = value;
            cur_size++;
            heapifyUp();
        }
        else
            display("No space in heap!");
    }
    
    public static int poll()  //take out root
    {
        int value = heap[0];
        heap[0] = heap[cur_size-1];
        cur_size--;
        heapifyDown();
        return value;
    }
    
    public static void heapifyUp()
    {
        //take last element and swap with parents till condition
        int workingIndex = cur_size-1;
        
        if(MODE==MIN_HEAP)
        {
            while(workingIndex>0&&heap[workingIndex]<getParentValue(workingIndex))
            {
                swap(workingIndex,getParentIndex(workingIndex));
                workingIndex = getParentIndex(workingIndex);
            }
        }
        else if(MODE==MAX_HEAP)
        {
            while(workingIndex>0&&heap[workingIndex]>getParentValue(workingIndex))
            {
                swap(workingIndex,getParentIndex(workingIndex));
                workingIndex = getParentIndex(workingIndex);
            }
        }
        
    }
    
     public static void heapifyDown()
     {
         int workingIndex = 0;
        
        if(MODE==MIN_HEAP)
        {
            while(hasLeftChild(workingIndex))
            {
                int tempindex;
                if(hasRightChild(workingIndex)&&(getRightChildValue(workingIndex)<getLeftChildValue(workingIndex)))
                    tempindex = getRightChildIndex(workingIndex);
                else    
                    tempindex = getLeftChildIndex(workingIndex);
                    
                if(heap[tempindex]<heap[workingIndex])
                {
                    swap(tempindex,workingIndex);
                    workingIndex=tempindex;
                }
                else break;
            }
        }
        else if(MODE==MAX_HEAP)
        {
            while(hasLeftChild(workingIndex))
            {
                int tempindex;
                if(hasRightChild(workingIndex)&&(getRightChildValue(workingIndex)>getLeftChildValue(workingIndex)))
                    tempindex = getRightChildIndex(workingIndex);
                else    
                    tempindex = getLeftChildIndex(workingIndex);
                    
                if(heap[tempindex]>heap[workingIndex])
                {
                    swap(tempindex,workingIndex);
                    workingIndex=tempindex;
                }
                else break;
            }
        }
     }
    
	public static void main (String[] args) {
	    init(MIN_HEAP);     //set mode
	    insert(10);
	    insert(15);
	    insert(20);
	    insert(17);
	 
	    insert(25);
	    showHeap();
	    
	    poll();
	    showHeap();
	    
		
	}
}