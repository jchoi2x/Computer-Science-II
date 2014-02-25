
public class Vertex {
	public Integer nodeNum ;
	public Vertex[] adjacents ;
	public int numAdjs ;
	public int pushTime ;
	public int popTime ;
    public Boolean visited ;
    public Boolean dequeued ;
	public Vertex(Integer name){
		nodeNum = name ;
		pushTime = -1 ;
		popTime = -1 ;
		adjacents = new Vertex[50];
		numAdjs = 0 ;
        visited = false;
        dequeued = false ; 
	}

	public void setDequeued(){
		dequeued=true;
	}
	public int addAdjacent(Vertex v){
		adjacents[numAdjs] = v ;
		numAdjs++ ;
		return 1 ;
	}
	public Vertex getAdjacents(int i){
		return this.adjacents[i];
	}
	public void deleteAdjacents(){
		adjacents = new Vertex[50];
		numAdjs = 0 ; 
	}
	public Vertex[] getAdjacents(){
		return this.adjacents;
	}
	public int getNumAdjs(){
		return this.numAdjs;
	}
	public int setPushTime(int time){
		this.pushTime=time ;
		return 1 ;
	}
    public int getName(){
        return this.nodeNum;
    }
	public int setPopTime(int time){
		this.popTime = time ;
		return 1 ;
	}
    public void setVisited(){
        visited = true ;
    }
    public Boolean isVisited(){
        return visited;
    }

}
