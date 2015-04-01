import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer; 

public class FileSearch  {
	
	static String name;
	Thread t;
	String st;
	Map<String, String> m = new HashMap<String, String>();
	
	public static int a;
	     FileSearch() {
	   
	   }
	public void results (){
		int count = getResult().size();
    	if(count ==0){
    	    System.out.println("\nNo result found!");
    	}else{
    	    System.out.println("\nFound " + count + " result!\n");
    	    for (String matched :getResult()){
    		System.out.println("Found : " + matched);
    	    }
    	}
		
	}
	public Runnable run(String str) {
        System.out.println("Hello from a thread!");
        if( ! str.contains("C:")){
        searchDirectory(new File(str), name);
                }
		return t;
    }

 
  private String fileNameToSearch;
  private List<String> result = new ArrayList<String>();
 
  public String getFileNameToSearch() {
	return fileNameToSearch;
  }
 
  public void setFileNameToSearch(String fileNameToSearch) {
	this.fileNameToSearch = fileNameToSearch;
  }
 
  public List<String> getResult() {
	return result;
  }

  public static void main(String[] args) {
 
	FileSearch fileSearch = new FileSearch();
	   System.out.println("Enter the name of thee file you want to search\n");
	   Scanner scan = new Scanner(System.in);
		
		name = scan.nextLine();
	   File[] paths;
	   paths = File.listRoots();
       int count=0;
       
       paths = File.listRoots();
       for(File path:paths)
       {
       	System.out.println("Searching in..");
          System.out.println(path);
       
        String str =   path.toString();
       File dir = new File(str);
       File[] files = dir.listFiles();
       FileFilter fileFilter = new FileFilter() {
          public boolean accept(File file) {
             return file.isDirectory();
          }
       };
       files = dir.listFiles(fileFilter);
       System.out.println(files.length);
       try{
       if (files.length == 0) {
          System.out.println("Either dir does not exist or is not a directory");
       }
       else {
          for (int i=0; i< files.length; i++) {
        	  count++;
          }
       }}
       
       catch(NullPointerException np){
    	   }
       }
       System.out.println(count);
       ArrayList<Thread> thread = new ArrayList<Thread>(count);
       paths = File.listRoots();

      	   
      	 for(File path:paths)
         {
         	System.out.println("Searching in..");
            System.out.println(path);
         
          String str =   path.toString();
         File dir = new File(str);
         File[] files = dir.listFiles();
         FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
               return file.isDirectory();
            }
         };
         files = dir.listFiles(fileFilter);
        // System.out.println(files.length);
         if (files.length == 0) {
            System.out.println("Either dir does not exist or is not a directory");
         }
         else {
            for (int i=0; i< files.length; i++) {
            	 File filename = files[i];
             //    System.out.println(filename.toString());
                 Thread t = new Thread(fileSearch.run(filename.toString()));
             	 thread.add(t);
             	 t.start();
             	   
            }
         }}
       
      	fileSearch.results();
   
      	
  }
 

public void searchDirectory(File directory, String fileNameToSearch) {
 
	setFileNameToSearch(fileNameToSearch);
 
	if (directory.isDirectory()) {
	    search(directory);
	} else {
	    System.out.println(directory.getAbsoluteFile() + " is not a directory!");
	}
 
  }
 
  private void search(File file) {
 
	if (file.isDirectory()) {
	 // System.out.println("Searching directory ... " + file.getAbsoluteFile());
 
            //do you have permission to read this directory?	
	    if (file.canRead()) {
	    	try {
		for (File temp : file.listFiles()) {
		    if (temp.isDirectory()) {
			search(temp);
		    } else {
			if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {			
				System.out.println(temp.getAbsoluteFile().toString());
			    result.add(temp.getAbsoluteFile().toString());
		    }
			StringTokenizer st = new StringTokenizer(temp.getAbsoluteFile().toString(), "\\"); 
			String t1 = temp.getAbsoluteFile().toString();
			int i =0;
			String[] topp = new String[100];
			while(st.hasMoreTokens()) { 
			String key = st.nextToken(); 
			topp[i] = key;
			i++;
			} 
			//System.out.println(topp[i-1]);
			String tempst = topp[0];
			String s="\\";
			for(int k = 1;k< i-2;k++){
				
				tempst+=s;
				tempst+=topp[k];
				//System.out.println(tempst);
			}
			tempst+=s;
			//System.out.println(tempst);
			 m.put(topp[i-1],tempst );
			// System.out.println(m);
		}
			
	    }
	    	}
	    	catch (NullPointerException npe) {
	    	    
	    	}
 
	 } else {
		System.out.println(file.getAbsoluteFile() + "Permission Denied");
	 }
      }
 
  }
 
}
