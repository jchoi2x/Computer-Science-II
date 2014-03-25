#include <stdio.h>
#include <stdlib.h>

struct tree_node {
    int data;
    struct tree_node *left;
    struct tree_node *right;
};

struct tree_node *create_node(int val);
void inorder(struct tree_node *current_ptr);
struct tree_node* insert(struct tree_node *root, struct tree_node *element);
int add(struct tree_node *current_ptr);

int find(struct tree_node *current_ptr, int val)j
int height(struct tree_node * root);

struct tree_node* parent(struct tree_node *root, struct tree_node *node);
struct tree_node* minVal(struct tree_node *root);
struct tree_node* maxVal(struct tree_node *root);
int isLeaf(struct tree_node *node);
int hasOnlyLeftChild(struct tree_node *node);
int hasOnlyRightChild(struct tree_node *node);
struct tree_node* findNode(struct tree_node *current_ptr, int value);
struct tree_node* delete(struct tree_node* root, int value);

int max(int val1, int val2);

int menu();

int main() {
    struct tree_node *my_root=NULL, *temp_node;
    int done = 0,ans=1, val;

    ans = menu();
    while (ans<7) {

        if (ans == 1) {
            // Get value to insert.
            printf("What value would you like to insert?\n");
            scanf("%d", &val);
            temp_node = create_node(val); // Create the node.
            // Insert the value.
            my_root = insert(my_root, temp_node);
        }

        else if (ans == 2) {
            printf("What value would you like to delete?\n");
            scanf("%d", &val);
            if (!find(my_root, val))
                printf("Sorry that value isn't in the tree to delete.\n");
            else {
                my_root = delete(my_root, val);
            }
        }

        else if (ans == 3) {
            printf("What value would you like to search for?\n");
            scanf("%d", &val);
            if (find(my_root, val))
                printf(" Found %d in the tree.\n", val);
            else
                printf(" Did not find %d in the tree.\n", val);
        }
        else if (ans == 4)
            printf("The sum of the nodes in your tree is %d.\n", add(my_root));

        else if (ans == 5) {
            // Print the resulting tree.
            printf("Here is an inorder traversal of your tree: ");
            inorder(my_root);
            printf("\n");
        }
        else if (ans == 6){
            printf("Height: %d\n",height(my_root));
        }
        else {
            return 0 ;
        }
        // See if they want to insert more nodes.
        ans = menu();
    }
    return 0;
}
int height(struct tree_node * root ){
    if (root == NULL) return -1 ;
    return max(height(root->left),height(root->right))+1;
}
int max(int val1, int val2){
    if (val1 > val2) return val1 ;
    else return val2 ;
}
void inorder(struct tree_node *current_ptr) {

    // Only traverse the node if it's not null.
    if (current_ptr != NULL) {
        inorder(current_ptr->left); // Go Left.
        printf("%d ", current_ptr->data); // Print the root.
        inorder(current_ptr->right); // Go Right.
    }
}

struct tree_node* insert(struct tree_node *root,
        struct tree_node *element) {

    // Inserting into an empty tree.
    if (root == NULL)
        return element;
    else {

        // element should be inserted to the right.
        if (element->data > root->data) {

            // There is a right subtree to insert the node.
            if (root->right != NULL)
                root->right = insert(root->right, element);

            // Place the node directly to the right of root.
            else
                root->right = element;
        }

        // element should be inserted to the left.
        else {

            // There is a left subtree to insert the node.
            if (root->left != NULL)
                root->left = insert(root->left, element);

            // Place the node directly to the left of root.
            else
                root->left = element;
        }

        // Return the root pointer of the updated tree.
        return root;
    }
}

struct tree_node* create_node(int val) {

    // Allocate space for the node, set the fields.
    struct tree_node* temp;
    temp = (struct tree_node*)malloc(sizeof(struct tree_node));
    temp->data = val;
    temp->left = NULL;
    temp->right = NULL;

    return temp; // Return a pointer to the created node.
}

int find(struct tree_node *current_ptr, int val) {

    // Check if there are nodes in the tree.
    if (current_ptr != NULL) {
        // Found the value at the root.
        if (current_ptr->data == val) return 1;
        // Search to the left.
        if (val < current_ptr->data) return find(current_ptr->left, val);
        // Or...search to the right.
        else return find(current_ptr->right, val);
    }
    else return 0;
}

int add(struct tree_node *current_ptr) {
    if (current_ptr != NULL) return current_ptr->data+add(current_ptr->left)+add(current_ptr->right);
    else return 0;
}

/* Returns the parent of the node pointed to by node in the tree rooted at
 *  root. If the node is the root of the tree, or the node doesn't exist in
 *  the tree, null will be returned.*/
struct tree_node* parent(struct tree_node *root, struct tree_node *node) {
    // Take care of NULL cases.
    if (root == NULL || root == node) return NULL;
    // The root is the direct parent of node.
    if (root->left == node || root->right == node) return root;
    // Look for node's parent in the left side of the tree.
    if (node->data < root->data) return parent(root->left, node);
    // Look for node's parent in the right side of the tree.
    else if (node->data > root->data) return parent(root->right, node);

    return NULL; // Catch any other extraneous cases.
}

// Returns a pointer to the node storing the minimum value in the tree
// with the root, root. Will not work if called with an empty tree.
struct tree_node* minVal(struct tree_node *root) {
    // Root stores the minimal value.
    if (root->left == NULL) return root;
    // The left subtree of the root stores the minimal value.
    else return minVal(root->left);
}


// Returns a pointer to the node storing the maximum value in the tree
// with the root, root. Will not work if called with an empty tree.
struct tree_node* maxVal(struct tree_node *root) {
    // Root stores the maximal value.
    if (root->right == NULL) return root;
    // The right subtree of the root stores the maximal value.
    else return maxVal(root->right);
}

// Returns 1 if node is a leaf node, 0 otherwise.
int isLeaf(struct tree_node *node) {
    return (node->left == NULL && node->right == NULL);
}

// Returns 1 iff node has a left child and no right child.
int hasOnlyLeftChild(struct tree_node *node) {
    return (node->left!= NULL && node->right == NULL);
}

// Returns 1 iff node has a right child and no left child.
int hasOnlyRightChild(struct tree_node *node) {
    return (node->left== NULL && node->right != NULL);
}

// Returns a pointer to a node that stores value in it in the subtree
// pointed to by current_ptr. NULL is returned if no such node is found.
struct tree_node* findNode(struct tree_node *current_ptr, int value) {
    // Check if there are nodes in the tree.
    if (current_ptr != NULL) {
        // Found the value at the root.
        if (current_ptr->data == value) return current_ptr;
        // Search to the left.
        if (value < current_ptr->data) return findNode(current_ptr->left, value);
        // Or...search to the right.
        else return findNode(current_ptr->right, value);
    }
    else return NULL; // No node found.
}

// Will delete the node storing value in the tree rooted at root. The
// value must be present in the tree in order for this function to work.
// The function returns a pointer to the root of the resulting tree.
struct tree_node* delete(struct tree_node* root, int value) {

    struct tree_node *delnode, *new_del_node, *save_node;
    struct tree_node *par;
    int save_val;

    delnode = findNode(root, value); // Get a pointer to the node to delete.

    par = parent(root, delnode); // Get the parent of this node.

    // Take care of the case where the node to delete is a leaf node.
    if (isLeaf(delnode)) {

        // Deleting the only node in the tree.
        if (par == NULL) {
            free(root); // free the memory for the node.
            return NULL;
        }

        // Deletes the node if it's a left child.
        if (value < par->data) {
            free(par->left); // Free the memory for the node.
            par->left = NULL;
        }

        // Deletes the node if it's a right child.
        else {
            free(par->right); // Free the memory for the node.
            par->right = NULL;
        }

        return root; // Return the root of the new tree.
    }

    // Take care of the case where the node to be deleted only has a left
    // child.
    if (hasOnlyLeftChild(delnode)) {
        // Deleting the root node of the tree.
        if (par == NULL) {
            save_node = delnode->left;
            free(delnode); // Free the node to delete.
            return save_node; // Return the new root node of the resulting tree.
        }
        // Deletes the node if it's a left child.
        if (value < par->data) {
            save_node = par->left; // Save the node to delete.
            par->left = par->left->left; // Readjust the parent pointer.
            free(save_node); // Free the memory for the deleted node.
        }
        // Deletes the node if it's a right child.
        else {
            save_node = par->right; // Save the node to delete.
            par->right = par->right->left; // Readjust the parent pointer.
            free(save_node); // Free the memory for the deleted node.
        }
        return root; // Return the root of the tree after the deletion.
    }
    // Takes care of the case where the deleted node only has a right child.
    if (hasOnlyRightChild(delnode)) {
        // Node to delete is the root node.
        if (par == NULL) {
            save_node = delnode->right;
            free(delnode);
            return save_node;
        }
        // Delete's the node if it is a left child.
        if (value < par->data) {
            save_node = par->left;
            par->left = par->left->right;
            free(save_node);
        }
        // Delete's the node if it is a right child.
        else {
            save_node = par->right;
            par->right = par->right->right;
            free(save_node);
        }
        return root;
    }

    // Find the new physical node to delete.
    new_del_node = minVal(delnode->right);
    save_val = new_del_node->data;

    delete(root, save_val);  // Now, delete the proper value.

    // Restore the data to the original node to be deleted.
    delnode->data = save_val;

    return root;
}

// Prints out the menu of choices for the user and returns their choice.
int menu() {

    int ans;
    printf("Here are your choices.\n");
    printf("1. Insert an item into your tree.\n");
    printf("2. Delete an item from your tree.\n");
    printf("3. Search for an item in your tree.\n");
    printf("4. Print the sum of the nodes in your tree.\n");
    printf("5. Print out an inorder traversal of your tree.\n");
    printf("6. Print Height.\n");
    printf("7. Quit.\n");
    scanf("%d", &ans);
    return ans;
}
