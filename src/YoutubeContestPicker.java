
	import org.jsoup.*;
	import org.jsoup.nodes.Document;
	import org.jsoup.nodes.Element;
	import org.jsoup.select.Elements;
	import java.util.*;

	public class YoutubeContestPicker {
	
		public static void main(String[] args) throws Exception {
	        
			int k = 0;
			int y = 0;
			int q = 0;
			int commentCounter = 0;
			boolean test = false;
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Please Enter video Url here >>>>>>>>");
			String url = scan.nextLine().replace("watch", "all_comments");
			//String url = "https://www.youtube.com/all_comments?v=iNAoGdnpaYY";	
			Document document = Jsoup.connect(url).get();

			//gets the comments text
	        Elements commentLocation = document.select(".comment-text-content");
			ArrayList<String> comments = new ArrayList();
			for (Element comment : commentLocation){
				comments.add(comment.text());
				commentCounter++;
			}
			
			//gets the commenter names
			Elements commenterLocation = document.select(".comment-header a");
			ArrayList<String> commenters = new ArrayList();
			for(Element name : commenterLocation){
				//gets rid of date
				if (y % 2 == 0)
				commenters.add(name.text());
				
				else;
				
				y ++;
			}
			//gets the user profile link
			Elements userProfileLocation = document.select(".user-photo a[href]");
			ArrayList<String> profiles = new ArrayList();
			for (Element links : userProfileLocation){	
				profiles.add(links.attr("abs:href"));
			}
			
			ArrayList<Person> people = new ArrayList();
			while (k < comments.size()){
				
				Person person = new Person(commenters.get(k),comments.get(k),profiles.get(k));
				if (k == 0){
					people.add(person);	
				}
				
				else {
					while (q < people.size()){
						 if (Person.personEqual(person,people.get(q))){
							test = true;
						}
						q++;
					}
					
					if (test == false){
						people.add(person);
					}
					else;
				}
				
				q = 0;
				test = false;
				k++;
			}


			int Random = (int) (Math.random()*people.size());
			System.out.println("The Total number of comments is: " + people.size() +" Comment counter orgigninal " + commentCounter + "\n\nThe winner is ...\n************************************\n" + people.get(Random));
		}		
	}

	class Person{
		private String Name;
		private String Comment;
		private String Profile;
		
		public Person(String Name, String Comment, String Profile){
			this.Name = Name;
			this.Comment = Comment;
			this.Profile = Profile;
		}
		
		public String getName(){return Name;}
		
		public String getProfile(){return Profile;}
		
		public String getComment(){return Comment;}
		
		public static boolean personEqual(Person person1, Person person2){
			
			if ((person1.getName()).equals(person2.getName())){return true;}
			
			else {return false;}	
		}
		public String toString(){
			return "Name: " + Name + "\nComment: " + Comment + "\nLink To Profile: " + Profile + "\n";
		}
	}


