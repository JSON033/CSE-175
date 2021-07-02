import java.util.HashSet;


public class DFSearch {


	Map graph;
	public String initialLoc;
	public String destinationLoc;
	int limit = 1000;
	public int nodeExpansionCount = 0;

	public DFSearch(){

	}
	public DFSearch(Map graph, String initialLoc, String destinationLoc, int limit) {
		this.initialLoc = initialLoc;
		this.destinationLoc = destinationLoc;
		this.graph = graph;
		this.limit = limit;

	}
	public Node search(boolean repeatedstatecheck) {
		nodeExpansionCount = 0;
		
		Location startloc = graph.findLocation(initialLoc);
		
		Node startNode = new Node(startloc);

		Frontier stack = new Frontier();

		stack.addToTop(startNode);

		HashSet<String> explored = new HashSet<String>();

		while(!stack.isEmpty()){

			Node currentloc = stack.removeTop();

			if (currentloc.isDestination(destinationLoc)){
				return currentloc;
			}


			explored.add(currentloc.loc.name);



			currentloc.expand();
			nodeExpansionCount++;
			if(nodeExpansionCount >= limit){
				break;
			}

			for(int i = 0; i < currentloc.children.size(); i++){
				if (repeatedstatecheck){
					if(!explored.contains(currentloc.children.get(i).loc.name) && !stack.contains(currentloc.children.get(i)) ){
						stack.addToTop(currentloc.children.get(i));


					}
				}
				else if (!stack.contains(currentloc.children.get(i))){
					stack.addToTop(currentloc.children.get(i));

				}
			}
		}

		return null;
	}

}
