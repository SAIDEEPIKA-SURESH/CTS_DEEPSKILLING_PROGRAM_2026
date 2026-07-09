package exercise6;

public class LibrarySearch {

    static int linearSearch(Book[] books,
                            String title) {

        for(int i=0;i<books.length;i++) {
            if(books[i].title.equalsIgnoreCase(title))
                return i;
        }
        return -1;
    }

    static int binarySearch(Book[] books,
                            String title) {

        int low=0,high=books.length-1;

        while(low<=high) {
            int mid=(low+high)/2;

            int result=
            books[mid].title.compareToIgnoreCase(title);

            if(result==0)
                return mid;
            else if(result<0)
                low=mid+1;
            else
                high=mid-1;
        }
        return -1;
    }

    public static void main(String[] args) {

        Book[] books = {
            new Book(1,"C","Author1"),
            new Book(2,"Java","Author2"),
            new Book(3,"Python","Author3")
        };

        System.out.println(
            "Linear Search Index: " + linearSearch(books,"Java"));

        System.out.println(
            "Binary Search Index: " + binarySearch(books,"Java"));
    }
}
