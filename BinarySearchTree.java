import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTree {
    Node root;

    public static void main(String[] args) {
        int max = 100; // 랜덤 수의 최대 가능한 범위
        int length = 10; // 랜덤 수 추출 길이
        int[] randomNumbers = AlgorithmUtil.generateRandomNumbers(max, length);
        int[] numbers = new int[length];
        numbers = randomNumbers.clone();

        /**
         * Binary tree 제작
         */
        BinarySearchTree tree = new BinarySearchTree();
        for (int number : numbers) {
            tree.insert(number);
        }

        BTreePrinter.printNode(tree.root);
        System.out.println("제거 : " + numbers[3]);
        tree.delete(numbers[3]); // 임의로 numbers[3]을 삭제
        BTreePrinter.printNode(tree.root);
    }

    /**
     * 데이터를 이진 탐색 트리에 삽입
     * root값을 이용하여 재귀함수를 호출한다.
     * @param element 삽입 데이터
     */
    public void insert(int element) {

        /**
         * 초기 root가 없을 경우 root를 새로운 노드로 만든다.
         */
        if (root == null) {
            root = new Node(element);
            return;
        }

        insert(root, element);
    }

    /**
     * 초기 root를 대입받아 이진 탐색 트리의 적절한 곳에 데이터 삽입
     * @param node 노드
     * @param element 삽입 데이터
     * @return
     */
    private Node insert(Node node, int element) {

        /**
         * 노드가 null일 경우 즉, 적절한 곳에 노드 위치를 찾은 경우
         * 데이터를 삽입 받아 노드를 하나 생성한다.
         * node를 반환했기 때문에 부모노드는 자신을 참조할 수 있다.
         */
        if (node == null) {
            node = new Node(element);
            return node;
        }

        /**
         * 삽입 데이터가 현재 가리키고 있는 노드의 데이터 값보다 작은 경우
         * 현재 노드의 왼쪽에 노드가 위치한다.
         */
        if (element < node.data) {
            node.left = insert(node.left, element);

        /**
         * 삽입 데이터가 현재 가리키고 있는 노드의 데이터 값보다 큰 경우
         * 현재 노드의 오른쪽에 노드가 위치한다.
         */
        } else if (element > node.data) {
            node.right = insert(node.right, element);
        }

        /**
         * 삽입 데이터와 현재 가리키는 노드의 값이 동일한 경우 자기 자신을 반환한다.
         * 이진 탐색 트리는 중복을 허용하지 않는다.
         */
        return node;
    }

    /**
     * 데이터를 이진 탐색 트리에서 검색
     * root값을 이용하여 재귀함수를 호출한다.
     * @param element 검색 값
     * @return
     */
    public boolean search(int element) {
        Node result = search(root, element);
        return result != null;
    }
    
    /**
     * 데이터를 이진 탐색 트리에서 검색하는 재귀 함수
     * 초기 root를 node로 가져와 수를 비교하며,
     * 비교값에 따라 node의 left, right로 이동한다.
     * @param node 현재 가리키는 노드
     * @param element 검색 값
     * @return
     */
    private Node search(Node node, int element) {

        /**
         * node에 값이 없을 경우, 말단에 도착했음에도 불구하고 없다는 의미이다.
         * node의 데이터와 값이 일치하는 경우 해당하는 노드를 반환한다.
         */
        if (node == null || node.data == element) {
            return node;
        }

        /**
         * 검색하려 하는 값이 node의 데이터보다 작은 경우
         * node의 left child로 넘어가야 한다.
         */
        if (element < node.data) {
            return search(node.left, element);
        }

        /**
         * 검색하려 하는 값이 node의 데이터보다 큰 경우
         * node의 right child로 넘어가야 한다.
         * 값이 일치하는 경우나 null인 경우에는 함수 초기에 catch했으므로
         * 남은 부분은 node의 right를 가리키는 것이다.
         */
        return search(node.right, element);
    }

    /**
     * 데이터를 이진 탐색 트리에서 삭제
     * root값을 이용하여 재귀함수를 호출한다.
     * @param element 삭제 값
     */
    public void delete(int element) {
        root = delete(root, element);
    }
    

    private Node delete(Node node, int element) {

        /**
         * node가 null일 경우 node를 반환한다.
         */
        if (node == null) {
            return node;
        }

        /**
         * 삭제하려는 값이 node의 데이터보다 작은 경우
         * 현재 node의 left는 삭제 작업 이후의 반환된 node를 가리켜야 한다.
         */
        if (element < node.data) {
            node.left = delete(node.left, element);

        /**
         * 삭제하려는 값이 node의 데이터보다 큰 경우
         * 현재 node의 right는 삭제 작업 이후의 반환된 node를 가리켜야 한다.
         */
        } else if (element > node.data) {
            node.right = delete(node.right, element);

        /**
         * 삭제하려는 값과 현재 node의 데이터가 같은 경우
         * case 1 : 자식이 없는 경우
         * case 2 : 자식이 하나 있는 경우
         * case 3 : 자식이 두개 있는 경우
         */
        } else {

            /**
             * node의 left가 비어있는 경우 node의 right를 반환한다.
             * 이 때에 right값이 없는 경우에는 case 1의 형태가 되며
             * right값이 있는 경우에는 case 2의 형태가 된다.
             */
            if (node.left == null) {
                return node.right;

            /**
             * node의 right가 비어있는 경우 node의 left를 반환한다.
             * 여기까지 온 경우에는 left child가 있는 경우이므로
             * case 2의 형태가 된다.
             */
            } else if (node.right == null) {
                return node.left;
            }

            /**
             * case 3의 경우에는 2가지 방법으로 해결할 수 있다.
             * 여기에서는 node의 right child에서 가장 작은 수를 찾아 현재 노드를 대체하는 방법이다.
             * 따라서 가장 작은 수를 현재 node의 data에 대입하고,
             * 가리키고 있던 right child는 기존의 node가 갖고 있던 right를 따라 내려가며,
             * 앞으로 삭제해야 할 값을 현재의 노드 값에 대체된 값으로 바꾼다.
             */
            node.data = mindata(node.right);
            node.right = delete(node.right, node.data);
        }

        return node;
    }

    /**
     * node를 root로 하는 트리에서 가장 작은 값을 찾는다.
     * @param node 현재 노드
     * @return
     */
    private int mindata(Node node) {
        int min = node.data;

        /**
         * node의 left값이 현재 node보다 작은 값이므로
         * left의 값이 존재할 경우 그 값을 최솟값으로 잡는다.
         */
        while (node.left != null) {
            min = node.left.data;
            node = node.left;
        }
        return min;
    }

    /**
     * Tree에 들어가는 노드
     */
    private class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Tree를 시각적으로 표현하기 위한 클래스
     * 참조 : https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
     */
    private class BTreePrinter {

        public static void printNode(Node node) {
            int maxLevel = BTreePrinter.maxLevel(node);
    
            printNodeInternal(Collections.singletonList(node), 1, maxLevel);
        }
    
        private static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
                return;
    
            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
    
            BTreePrinter.printWhitespaces(firstSpaces);
    
            List<Node> newNodes = new ArrayList<Node>();
            for (Node node : nodes) {
                if (node != null) {
                    System.out.print(node.data);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }
    
                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");
    
            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }
    
                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        BTreePrinter.printWhitespaces(1);
    
                    BTreePrinter.printWhitespaces(i + i - 1);
    
                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        BTreePrinter.printWhitespaces(1);
    
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }
    
                System.out.println("");
            }
    
            printNodeInternal(newNodes, level + 1, maxLevel);
        }
    
        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }
    
        private static <T extends Comparable<?>> int maxLevel(Node node) {
            if (node == null)
                return 0;
    
            return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
        }
    
        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }
    
            return true;
        }
    
    }
}