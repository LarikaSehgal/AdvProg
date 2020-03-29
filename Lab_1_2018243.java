import java.util.* ;
class MarksforCompany{
	Student s; int score ;
	MarksforCompany(Student s) {
		this.s = s;
		this.score = 0;
	}
}
class Company {
	boolean open = true ; int limit ; String name ;
	String[] criteria ;
	ArrayList<MarksforCompany> students_tech_score = new ArrayList<MarksforCompany>();
	Company(String name ,int limit,String[] criteria,ArrayList<Student> st){
		this.name = name;
		this.limit = limit;
		this.criteria = criteria ;
		display();
		findstudents(st);
	}
	void display() {
		System.out.println(this.name);
		System.out.println("Course criteria");
		for (int i = 0; i<criteria.length;i++) {
			System.out.println(criteria[i]);
		}
		System.out.println("Number Of Required Students = " + Integer.toString(this.limit));
		if (this.open == true) {
			System.out.println("Application Status = OPEN");
		}
		if (this.open == false) {
			System.out.println("Application Status = CLOSED");
		}
	}
	void findstudents(ArrayList<Student> st) {
		
		for(int i = 0; i<st.size(); i++) {
			if (st.get(i).placed == false) {
				String branch = st.get(i).branch ;
				for (int j =0 ; j<criteria.length;j++) {
					if (branch.matches(criteria[j])) {
						MarksforCompany v = new MarksforCompany(st.get(i));
						students_tech_score.add(v);
					}
				}
			}
		}
	}
}
class Student{
	int rno ;float cgpa ; String branch ; boolean placed; Company comp;
	Student(float cgpa , String branch,int rno){
		this.rno = rno ;
		this.cgpa = cgpa;
		this.branch = branch ;
		this.placed = false;
	}
}
public class Lab_1_2018243 {
	static ArrayList<Student> students = new ArrayList<Student>() ;
	static ArrayList<Company> companies = new ArrayList<Company>() ;
	public static boolean allplaced() {
		for (int i=0; i<students.size(); i++) 
			if (students.get(i).placed == false) {
				return false;
			}
		return true;
	}
	public static void sort(ArrayList<MarksforCompany> mfc) {
		ArrayList<MarksforCompany> arr = new ArrayList<MarksforCompany>();
		for (int i =0 ; i<mfc.size();i++) {
			if (mfc.get(i).s.placed == true) {
				arr.add(mfc.get(i));
			}
		}
		for (int i =0 ; i<arr.size();i++) {
			mfc.remove(arr.get(i));
		}
		for (int i =0 ;i <mfc.size();i++) {
			for (int j = 0 ; j<mfc.size()-i-1;j++) {
				if (mfc.get(j).score < mfc.get(j+1).score) {
					MarksforCompany temp = mfc.get(j);
					mfc.set(j, mfc.get(j+1));
					mfc.set(j+1, temp);
				}
				if (mfc.get(j).score ==  mfc.get(j+1).score && mfc.get(j).s.cgpa < mfc.get(j+1).s.cgpa) {
					MarksforCompany temp = mfc.get(j);
					mfc.set(j, mfc.get(j+1));
					mfc.set(j+1, temp);
				}
			}
		}
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		for (int i = 1 ; i<= n ; i++) {
			float gpa = s.nextFloat();
			String branch = s.next();
			Student st = new Student(gpa,branch,i);
			students.add(st);
		}
		while (allplaced() == false) {
			int q = s.nextInt();
			if (q==1){
				String name = s.next();
				System.out.print("Number Of Eligible Courses ="); 
				int c = s.nextInt();
				String[] arr = new String[c];
				for (int i = 0; i<c ; i++) {
					arr[i] = s.next();
				}
				System.out.print("Number Of Required Students =");
				int limit = s.nextInt();
				Company cp = new Company(name,limit,arr,students);
				companies.add(cp);
				if (cp.students_tech_score.size()>0){
					System.out.println("Enter scores for the technical round.");
					for (int i = 0 ; i<cp.students_tech_score.size();i++) {
						System.out.println("Enter score for Roll no." + Integer.toString(cp.students_tech_score.get(i).s.rno));
						int v = s.nextInt();
						cp.students_tech_score.get(i).score = v ;
					}
				}
				else {
					System.out.println("There is no student eligible for this company.");
				}
			}
			if (q==2){
				System.out.println("Accounts removed for");
				ArrayList<Student> arr = new ArrayList<Student>() ;
				for (int i = 0 ; i <students.size();i++) {
					if (students.get(i).placed == true) {
						System.out.println(students.get(i).rno);
						arr.add(students.get(i)) ;
					}
				}
				for (int i = 0; i<arr.size();i++) {
					students.remove(arr.get(i));
				}
				if (arr.size()== 0) {
					System.out.println("None of the students");
				}
			}
			if (q==3){
				ArrayList<Company> arr = new ArrayList<Company>() ;
				System.out.println("Accounts removed for");
				for (int i = 0 ; i <companies.size();i++) {
					if (companies.get(i).open == false) {
						System.out.println(companies.get(i).name);
						arr.add(companies.get(i)) ;
					}
				}
				for (int i = 0; i<arr.size();i++) {
					companies.remove(arr.get(i));
				}
				if (arr.size()== 0) {
					System.out.println("None of the companies");
				}
			}
			if (q==4){
				int count = 0 ;
				for (int i = 0; i<students.size();i++) {
					if (students.get(i).placed == false) {
						count += 1;
					}
				}
				System.out.println(Integer.toString(count) + " students left.");
			}
			if (q==5){
				for (int i = 0; i<companies.size();i++) {
					if (companies.get(i).open == true) {
						System.out.println(companies.get(i).name);
					}
				}
			}
			if (q==6){	
				String company = s.next();
				Company comp ; boolean sol = false;
				for (int i = 0 ; i<companies.size();i++) {
					if (companies.get(i).name.matches(company)) {	
						comp = companies.get(i);
						sort(comp.students_tech_score);
						if (comp.open == true) {
						if (comp.students_tech_score.size() >0) {
							
							System.out.println("Roll No. of the students selected:");
							int val = comp.limit ;
							if (val > comp.students_tech_score.size()) {
								val = comp.students_tech_score.size();
							}
							for (int j = 0 ;j <val ;j++) {
								Student st = comp.students_tech_score.get(j).s ;
								st.placed = true;
								st.comp = comp ;
								System.out.println(st.rno);
							
							}
							comp.open = false;
							sol = true;
							break;
						}
					
						else{
							System.out.println("No student is eligible for this company.");
							sol = true;
							comp.open = false;
						}}
						else {
							System.out.print("The placements for this company have been done but the account has not been deleted.");
						}
					}
				}
				
				if (sol == false) {
					System.out.println("No company with the given name has an account.");
				}
			}
			if (q==7){
				String name = s.next();
				boolean ans = false;
				for (int i = 0 ; i<companies.size();i++) {
					if (companies.get(i).name.matches(name)) {
						companies.get(i).display();
						ans = true;
						break;
					}
				}
				if (ans == false) {
					System.out.println("No company with the given name has an account.");
				}
			}
			if (q==8){
				int rno = s.nextInt();
				boolean ans = false;
				for (int i = 0 ; i<students.size();i++) {
					if (students.get(i).rno == rno) {
						Student st = students.get(i);
						System.out.println(st.rno);
						System.out.println(st.cgpa);
						System.out.println(st.branch);
						if (st.placed == true) {
							System.out.println("Placement Status: Placed");
							System.out.println(st.comp.name);
						}
						if (st.placed == false) {
							System.out.println("Placement Status: Not placed");
						}
						ans =true;
						break;
					}
					
				}
				if (ans == false) {
					System.out.println("No student with the given roll number has an account.");
				}
			}
			if (q==9){	
				int rno = s.nextInt();
				boolean ans = false; boolean got = false;		
				for (int i = 0 ; i<students.size();i++) {
					if (students.get(i).rno == rno) {
						got = true;
						
						for (int j= 0; j<companies.size() ; j++) {
							Company c = companies.get(j);
							for (int k = 0 ; k<c.students_tech_score.size();k++) {
								Student st = c.students_tech_score.get(k).s ;
								if (st.rno == rno) {
									ans = true;
									System.out.print(c.name + " ");
									System.out.println(c.students_tech_score.get(k).score);
								}
							}		
						}
					}
				
				}
				if (got == false) {
					System.out.println("No student with the given roll number has an account.");
				}
				if (got == true && ans == false) {
					System.out.println("The student is not eligible for any company");
				}
			}			
		}
	}
}
