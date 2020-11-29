/*************************************************************************
 * Name: Nicholas Mel
 * Description: A linked list is a sequence of nodes with efficient element insertion
 * and removal. This class contains a subset of the methods of the standard java.util.LinkedList class.
 *************************************************************************/

import java.util.NoSuchElementException;

public class LinkedList
{
   //nested class to represent a node
   private class Node
   {
          public Object data;
          public Node next;
   }

   //only instance variable that points to the first node.
   private Node first;

   // Constructs an empty linked list.
   public LinkedList()
   {
      first = null;
   }


   // Returns the first element in the linked list.
   public Object getFirst()
   {
      if (first == null)
       {
         NoSuchElementException ex = new NoSuchElementException();
         throw ex;
       }
      else
         return first.data;
   }

   // Removes the first element in the linked list.
   public Object removeFirst()
   {
      if (first == null)
       {
         NoSuchElementException ex = new NoSuchElementException();
         throw ex;
       }
      else
       {
         Object element = first.data;
         first = first.next;  //change the reference since it's removed.
         return element;
       }
   }

   // Adds an element to the front of the linked list.
   public void addFirst(Object element)
   {
      //create a new node
      Node newNode = new Node();
      newNode.data = element;
      newNode.next = first;
      //change the first reference to the new node.
      first = newNode;
   }

   // Returns an iterator for iterating through this list.
   public ListIterator listIterator()
   {
      return new LinkedListIterator();
   }


   /*********************************************************
   Add your methods here
   *********************************************************/
   //The toString method should concatenate strings in the linked list, and return a string of the following format:
   public String toString()
   {     // initialize your string variable and set it to "{"
	   String bracket = "{ ";
	   ListIterator iterator = listIterator();
	   while(iterator.hasNext())
	   {       //append (concatenate) the iterator.next() with your string
		   bracket += (iterator.next() + " ");
	   }
	   // append the " }\n" and return it
	   bracket += "}\n";
	   return bracket;
   }
   
   //The isEmpty method should return true if the link list is empty and return false otherwise.
   public boolean isEmpty()
   {
	   if(listIterator().hasNext())
	   {
		   return false;
	   }
	   else
	   {
		   return true;
	   }
   }
   
   public int size()
   {
	   ListIterator iterator = listIterator();
	   int count = 0;
	   while (iterator.hasNext())
	   {
		   count++;
		   iterator.next();
	   }
	   return count;
   }
   //The getElement method returns an element at the given index by its parameter. 
   //If the parameter index is larger or smaller than the existing indices, it should throw an object of the IndexOutOfBoundsException class.
   public Object getElement(int index)
   {
   //create a listIterator
	   ListIterator getIterator = listIterator(); // now you have access to the beginning of the list
   	   if(index < 0 || index > size())
       {
   		   throw new IndexOutOfBoundsException();
       }
   	   else
   	   {
   		   for(int i = 0; i < index; i++)
   		   {
   			   getIterator.next();
   		   }
   		   return getIterator.next();
       }
   }
   
   //The addElement adds the parameter element into the linked list. The linked list should contains all elements (strings) in alphabetical order. 
   //Therefore, in this addElement method, a correct location to insert the parameter element needs to be searched and the element needs to be inserted in that location.
   public void addElement(Object element)
   {
	   //Add iterator
	   ListIterator addIt = listIterator();
	   //initiate count variable
	   int count = 0;
	   //if list is empty
	   if(isEmpty())
	   {
		   addIt.add(element);
	   }
	   else
	   {
		   while(addIt.hasNext())
		   {
			   String addElement = (String) addIt.next();
			   String addElement2 = (String) element;
			   if(addElement2.compareTo(addElement) >= 0)
			   {
				   count++;
			   }	
		   }
		   //Add iterator
		   ListIterator addIt2 = listIterator();
		   for(int i = 0; i < count; i++)
		   {
			   addIt2.next();
		   }
		   addIt2.add(element);
   		}
   }
   
   
   //The addElements adds the parameter element into the linked list for the number of times specified by the parameter "howMany". 
   //The linked list should contains all elements (strings) in alphabetical order. 
   //Therefore, in this addElement method, a correct location to insert the parameter element needs to be searched and the element needs to be inserted in that location for the specified number of times. 
   public void addElements(Object element, int howMany)
   {
	 //Add iterator
	   ListIterator addIt2 = listIterator();
	   //initiate count variable
	   int count = 0;
	   //if list is empty
	   if(isEmpty())
	   {
		   addIt2.add(element);
	   }
	   else
	   {
		   while(addIt2.hasNext())
		   {
			   String addElement = (String) addIt2.next();
			   String addElement2 = (String) element;
			   if(addElement2.compareTo(addElement) >= 0)
			   {
				   count++;
			   }	
		   }
		   //Add iterator
		   for(int i = 0; i < howMany; i++)
		   {
			   addIt2.add(element);
		   }
   		}  	
   }

   
   //The searchAndRemove method searches the parameter element in the linked list and removes all occurrences of the element in the linked list. 
   //If the parameter element does not exist in the linked list, then the linked list should be unchanged.
   public void searchAndRemove(Object element)
   {
	   //Add iterator
	   ListIterator searchIt = listIterator();
	   //While loop to search
	   while(searchIt.hasNext())
	   	   {
			   String s = (String) searchIt.next();
			   if(s.equals(element))
			   {
				   searchIt.remove();
			   }
		   }
   }
 
   //The duplicateEach method makes a duplicate of each element in the linked list. 
   public void duplicateEach()
   {
	   //Add iterator
	   ListIterator duplicateIt = listIterator();
	   //While loop to duplicate
	   while(duplicateIt.hasNext())
	   {
		   String duplicate = (String)duplicateIt.next();
		   duplicateIt.add(duplicate);
	   }
	   
   }
   
   //nested class to define its iterator
   private class LinkedListIterator implements ListIterator
   {
      private Node position; //current position
      private Node previous; //it is used for remove() method

      // Constructs an iterator that points to the front
      // of the linked list.

      public LinkedListIterator()
      {
         position = null;
         previous = null;
      }

     // Tests if there is an element after the iterator position.
     public boolean hasNext()
      {
         if (position == null) //not traversed yet
          {
             if (first != null)
                return true;
             else
                return false;
          }
         else
           {
              if (position.next != null)
                 return true;
              else
                 return false;
           }
      }

      // Moves the iterator past the next element, and returns
      // the traversed element's data.
      public Object next()
      {
         if (!hasNext())
          {
           NoSuchElementException ex = new NoSuchElementException();
           throw ex;
          }
         else
          {
            previous = position; // Remember for remove

            if (position == null)
               position = first;
            else
               position = position.next;

            return position.data;
          }
      }

      // Adds an element before the iterator position
      // and moves the iterator past the inserted element.
      public void add(Object element)
      {
         if (position == null) //never traversed yet
         {
            addFirst(element);
            position = first;
         }
         else
         {
            //making a new node to add
            Node newNode = new Node();
            newNode.data = element;
            newNode.next = position.next;
            //change the link to insert the new node
            position.next = newNode;
            //move the position forward to the new node
            position = newNode;
         }
         //this means that we cannot call remove() right after add()
         previous = position;
      }

      // Removes the last traversed element. This method may
      // only be called after a call to the next() method.
      public void remove()
      {
         if (previous == position)  //not after next() is called
          {
            IllegalStateException ex = new IllegalStateException();
            throw ex;
          }
         else
          {
           if (position == first)
            {
              removeFirst();
            }
           else
            {
              previous.next = position.next; //removing
            }
           //stepping back
           //this also means that remove() cannot be called twice in a row.
           position = previous;
      }
      }

      // Sets the last traversed element to a different value.
      public void set(Object element)
      {
         if (position == null)
          {
            NoSuchElementException ex = new NoSuchElementException();
            throw ex;
          }
         else
          position.data = element;
      }
   } //end of LinkedListIterator class
} //end of LinkedList class