import java.util.HashSet;


public class BFSearch {


	Map graph;
	public String initialLoc;
	public String destinationLoc;
	int limit = 100000;
	public int nodeExpansionCount = 0;

	public BFSearch(){

	}
	public BFSearch(Map graph, String initialLoc, String destinationLoc, int limit) {
		this.initialLoc = initialLoc;
		this.destinationLoc = destinationLoc;
		this.graph = graph;
		this.limit = limit;

	}
	public Node search(boolean repeatedstatecheck) {
		nodeExpansionCount = 0;

		Location startloc = graph.findLocation(initialLoc);


		Node startNode = new Node(startloc);

		Frontier queue = new Frontier();

		queue.addToBottom(startNode);
	
			HashSet<String> explored = new HashSet<String>();
			
			
		
		while(!queue.isEmpty()){

			Node currentloc = queue.removeTop();

			if (currentloc.isDestination(destinationLoc)){
				return currentloc;
			}

			
				
				explored.add(currentloc.loc.name);
				
		



			currentloc.expand();
			nodeExpansionCount++;

			for(int i = 0; i < currentloc.children.size(); i++){
				if (repeatedstatecheck){
					if(!explored.contains(currentloc.children.get(i).loc.name) && !queue.contains(currentloc.children.get(i)) ){
						queue.addToBottom(currentloc.children.get(i));
						

					}
				}
				else  {
					queue.addToBottom(currentloc.children.get(i));
					
				}
			}
		}

		return null;
	}

}
