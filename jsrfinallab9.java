import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class hnode{
	int d;
	int f;
	hnode left,right;
	hnode(int dat,int freq){
		this.d=dat;
		this.f=freq;
	}
	hnode(){
		this.d=0;
		this.f=0;
	}
	
}

class minheaps{
	int size;
	hnode[] heap=new hnode[300];

	boolean isEmpty(){

		return this.size==1;
	}
	
	void datafreq(int data,int freq)
	{
		hnode h = new hnode(data,freq);
		this.insert(h);
	}
	void insert(hnode a)
	{
		
		int pos=this.size+1;
		while(pos>1&& a.f<this.heap[pos/2].f){
			this.heap[pos]=this.heap[pos/2];
			pos=pos/2;
			}
		this.size++;
		this.heap[pos]=a;
		
		
	}
	hnode deletemin()
	{
		assert(!isEmpty());
		hnode min=heap[1];
		this.size--;
		int npos=percolatedown(1,this.heap[this.size+1]);
		this.heap[npos]=this.heap[size+1];
		return min;
		
	}
	
	public int percolatedown(int hole,hnode h)
	{
		
		while(2*hole<=size){
		int left=2*hole;
		int right=left+1;
		int target;
		if(right<=size&&this.heap[right].f<this.heap[left].f){
			target=right;
		}
		else{
			target=left;
		}
		
		if(this.heap[target].f<h.f){
			this.heap[hole]=this.heap[target];
			hole=target;
		}
		else{
			break;
		}
		}
		return hole;
	}
	public static hnode hufftree(minheaps h){
		hnode root=new hnode();
		hnode left,right;
		
		while(!h.isEmpty())
		{
			left=h.deletemin();
			right=h.deletemin();
			root=new hnode(0,left.f+right.f);
			root.left=left;
			root.right=right;
			h.insert(root);
		}
		return root;
		
		
	}
	
	public static int countbits(hnode root,int branch)
	{
		if(root==null){
			return 0;
		}
		if(root.left==null&&root.right==null){
			
			if(branch==0){
				return root.f;
			}
			else{
			return root.f*branch;
			}
			
		}
		
		else{
			return countbits(root.left,branch+1)+countbits(root.right,branch+1);
		}
		
	
	}


}

public class jsrfinallab9 {
	public static void main (String[] args) throws java.lang.Exception{
		Reader.init(System.in);
		int width=Reader.nextInt();
		int height=Reader.nextInt();
		int[] c = new int[256];
		int[] d=new int[256];
		int[] arr = new int[height*width];
		for(int i=0;i<height*width;i++)
		{
			arr[i]=Reader.nextInt();
			int n=arr[i];
			c[n]++;
			d[n]++;
		
		
		}
		
		minheaps a = new minheaps();
		
		for(int i=0;i<256;i++){
			if(c[i]>0){
			
				a.datafreq(i,c[i]);
				
			
			}
		}
		
		int pk=0;
		int j=0;
		
		minheaps b = new minheaps();
		for(int i=0;i<25;i++){
			if(pk==240)
			{
				
				int max1=d[pk];
				int inmax=pk;
				for(j=pk;j<=pk+15;j++)
				{
					if(d[j]>0&&max1<d[j]){
						max1=d[j];
						inmax=j;
					}	
				}
				int f=0;
				for(int p=pk;p<=pk+15;p++){	
					if(d[p]>0){
						f=f+d[p];
					}
				}
				if(f>0){
				b.datafreq(inmax,f);
				}
				
			}
			else
			{
			int max=d[pk];
			int inmax=pk;
			for(j=pk;j<=pk+9;j++)
			{
				if(d[j]>0&&max<d[j]){
					max=d[j];
					inmax=j;
				}
				
			}
			int f=0;
			for(int p=pk;p<=pk+9;p++){
				if(d[p]>0){
					f=f+d[p];
				}
			}
			
			
			if(f>0){
			b.datafreq(inmax,f);
			}
			pk=pk+10;
		}
		
		}
		hnode r=new hnode();
		hnode r1=new hnode();
		if(a.size==1){
		
		r=a.heap[1];
		}
		else {
			r=a.hufftree(a);
			
		}
		if(b.size==1){
			r1=b.heap[1];
		}
		else if(b.size!=1){
			r1=b.hufftree(b);
		}
		
		int n=a.countbits(r,0);
		double ans=((double)(width*height*8)/n);
		double roundOff = Math.round(ans * 10.0) / 10.0;
		System.out.println(roundOff);
		int m=b.countbits(r1,0);
		double ans1=((double)(width*height*8)/m);
		double roundOff1 = Math.round(ans1 * 10.0) / 10.0;
		System.out.println(roundOff1);
		
		
		
	}

}
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}	