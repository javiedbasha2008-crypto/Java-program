public class ThroneInheritance {
    private String king;
    private Map<String, List<String>> familyTree;
    private Set<String> dead;

    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.familyTree = new HashMap<>();
        this.familyTree.put(kingName, new ArrayList<>());
        this.dead = new HashSet<>();
    }
    
    public void birth(String parentName, String childName) {
        // Add the child to the parent's list
        this.familyTree.putIfAbsent(parentName, new ArrayList<>());
        this.familyTree.get(parentName).add(childName);
        // Initialize the child's own list of children
        this.familyTree.put(childName, new ArrayList<>());
    }
    
    public void death(String name) {
        this.dead.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order);
        return order;
    }

    private void dfs(String currentPerson, List<String> order) {
        // If the current person is alive, add them to the order
        if (!dead.contains(currentPerson)) {
            order.add(currentPerson);
        }
        
        // Recursively visit all children in order of birth
        List<String> children = familyTree.get(currentPerson);
        if (children != null) {
            for (String child : children) {
                dfs(child, order);
            }
        }
    }
}