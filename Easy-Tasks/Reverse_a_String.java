public class Reverse_a_String {
    public static void main(String[] args) {
        String str = "Hi, Sergo";
        String newStr = reverse(str);
        System.out.println(newStr);
    }

    //static method so we can call it in the class
    public static String reverse(String str) {
        char[] charOfArray = str.toCharArray(); //we make the string into char array
        int left = 0; //beggining
        int right = charOfArray.length - 1; //end

        while(left < right) { //it's like swapping values of progression till we reach the middle
            char temp = charOfArray[left];
            charOfArray[left] = charOfArray[right];
            charOfArray[right] = temp;

            left++; //increasing till reaching the middle
            right--; //decreasing till reaching the middle
        }

        return new String(charOfArray); //returning the new object
    }
}

