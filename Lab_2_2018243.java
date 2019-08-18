import java.util.* ;
class item{
	String name ;int price ; int quantity ; String category ; String offer ; merchant merchant ;int code;
	item(String name ,int price , int quantity , String category , merchant merchant,int code){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.offer = "None";
		this.merchant = merchant;
		this.code = code;
	}
	void addOffer(String offer) {
		this.offer = offer;
	}
	void displayDetails() {
		System.out.print(this.code);
		System.out.print(" "+this.name + " ");
		System.out.print(this.price);
		System.out.print(" ");
		System.out.print(this.quantity);
		System.out.print(" "+this.offer +" ");
		System.out.print(this.category);
		System.out.println();
	}
}
class transaction{
	String name;double price;int quantity;merchant merchant;
	transaction(String name,double price,int quantity,merchant merchant){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.merchant = merchant;
	}
	void display() {
		System.out.print("Bought item ");
		System.out.print(this.name);
		System.out.print(" quantity: ");
		System.out.print(this.quantity);
		System.out.print(" for Rs");
		System.out.print(this.price);
		System.out.print(" from Merchant");
		System.out.print(this.merchant.name);
		System.out.println();
	}
}
class Company{
	int code = 1 ;
	private ArrayList<item> products = new ArrayList<item>();
	private ArrayList<customer> customers = new ArrayList<customer>();
	private ArrayList<merchant> merchants = new ArrayList<merchant>();
	private ArrayList<user> LoginHistory = new ArrayList<user>();
	private double AccBalance = 0 ;
	Company(){
		return;
	}
	void AddCustomer(customer c) {
		customers.add(c);
	}
	void AddMerchant(merchant m) {
		merchants.add(m);
	}
	void AddItem(String name ,int price , int quantity , String category , merchant mr){
		item item = new item(name,price,quantity,category,mr,code);
		boolean val = false;
		for (int i = 0; i<merchants.size();i++) {
			if (merchants.get(i) == mr){
				if (mr.slots >= mr.merchantproducts.size()) {
					mr.merchantproducts.add(item);
					this.products.add(item);
					code = code + 1 ;
					item.displayDetails();
					val = true;
				}
				else 
				{
					System.out.println("You have exhausted all your slots. Increase your contribution to get more slots.");
					val = true;
				}
			}
		}
		if (val == false) {
			System.out.println("There was some issue, your product has not been added.");
		}
	}
	public customer getCustomer(int x) {	
		return customers.get(x-1);
	}
	public merchant getMerchant(int x) {
		return merchants.get(x-1);
	}
	public item getItem(int x) {
		return products.get(x-1);
	}
	public void displayMenu(){
		System.out.println("Welcome to Mercury"); 
		System.out.println("1) Enter as Merchant");
		System.out.println("2) Enter as Customer");
		System.out.println("3) See user details");
		System.out.println("4) Company account balance");
		System.out.println("5) Exit");
	}
	public double getAccBalance() {
		return this.AccBalance ;
	}
	public void listItems() {
		for (int i =0 ;i<products.size();i++) {
			products.get(i).displayDetails();
		}
	}
	public ArrayList getCategories() {
		ArrayList<String> categories = new ArrayList<String>();
		for (int i = 0 ; i <products.size();i++) {
			boolean val = false;
			String cat = products.get(i).category ;
			for (int j =0 ;j <categories.size();j++) {
				if (categories.get(j).matches(cat)) {
					val = true;
					break;
				}
			}
			if (val == false) {
				categories.add(cat);
			}
		}
		for (int i =0 ; i<categories.size();i++) {
			System.out.println(Integer.toString(i+1) + " " +categories.get(i));		
		}
		return categories ;
	}
	public void search(String category) {
		for (int i = 0 ; i<products.size();i++) {
			if (products.get(i).category.matches(category)) {
				products.get(i).displayDetails();
			}
		}
	}
	public void listmerchantItems(merchant mr) {
		for (int i =0 ; i<products.size();i++) {
			if (products.get(i).merchant == mr) {
				products.get(i).displayDetails();
			}
		}
	}
	public void addBalance(double x) {
		this.AccBalance += x ;
	}
}
interface user{
	void displayMenu() ;
	void displayDetails();
	double getreward();
	
}
class customer implements user{
	String name;final int id;String Address; int no_orders; double reward = 0;double balance =100; double rewardbalance=0;
	ArrayList<transaction> transactions = new ArrayList<transaction>();
	ArrayList<transaction> cart = new ArrayList<transaction>();
	int rewardcounter=0 ;
	customer(String name,int id,String Address){
		this.name = name;
		this.id = id;
		this.Address= Address;
	}
	public void displayMenu() {
		System.out.println("Welcome " + this.name);
		System.out.println("Customer Menu");
		System.out.println("1 Ali");
		System.out.println("2 Noobi");
		System.out.println("3 Bruno");
		System.out.println("4 Borat");
		System.out.println("5 Aladeen");
	}
	public void displayDetails() {
		System.out.println(this.name);
		System.out.println(this.Address);
		System.out.println(this.no_orders);
	}
	public double getreward() {
		return reward;
	}
	void prevtansactions() {
		int val = 10;
		if (transactions.size()<10) {
			val = transactions.size();
		}
		for (int i =0 ; i<val ; i++) {
			transactions.get(i).display();
		}
	}
}
class merchant implements user{
	String name;final int id;String Address; double contribution=0; double rewardcounter =0; int slots = 10; double reward;
	ArrayList<item> merchantproducts = new ArrayList<item>();
	merchant(String name,int id,String Address){
		this.name = name;
		this.id = id;
		this.Address= Address;
	}
	public void displayMenu() {
		System.out.println("Welcome " + this.name);
		System.out.println("Merchant Menu");
		System.out.println("1) Add item");
		System.out.println("2) Edit item");
		System.out.println("3) Search by category");
		System.out.println("4) Add offer");
		System.out.println("5) Rewards won");
		System.out.println("6) Exit");
	}
	public void displayDetails() {
		System.out.println(this.name);
		System.out.println(this.Address);
		System.out.println(this.contribution);
	}
	public double getreward() {		
		return reward;
	}
}
public class Lab_2_2018243 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Company MercuryInc = new Company();	
		customer c1 = new customer("Ali",1,"Delhi"); 		MercuryInc.AddCustomer(c1);
		customer c2 = new customer("Noobi",2,"Delhi"); 		MercuryInc.AddCustomer(c2);
		customer c3 = new customer("Bruno",3,"Delhi"); 		MercuryInc.AddCustomer(c3);
		customer c4 = new customer("Borat",4,"Delhi");  	MercuryInc.AddCustomer(c4);
		customer c5 = new customer("Aladeen",5,"Delhi");	MercuryInc.AddCustomer(c5);		
		merchant m1 = new merchant("Jack",1,"Delhi"); 		MercuryInc.AddMerchant(m1);
		merchant m2 = new merchant("John",2,"Delhi"); 		MercuryInc.AddMerchant(m2);
		merchant m3 = new merchant("James",3,"Delhi"); 		MercuryInc.AddMerchant(m3);
		merchant m4 = new merchant("Jeff",4,"Delhi");		MercuryInc.AddMerchant(m4);
		merchant m5 = new merchant("Joseph",5,"Delhi"); 	MercuryInc.AddMerchant(m5);
		
		boolean val= true;
		while (val == true) 
		{
			MercuryInc.displayMenu();
			int input = s.nextInt();
			if (input == 1) 
			{
				System.out.println("Choose Merchant");
				System.out.println("1 jack");
				System.out.println("2 john");
				System.out.println("3 james");
				System.out.println("4 jeff");
				System.out.println("5 joseph");
				int m = s.nextInt();
				merchant mr = MercuryInc.getMerchant(m);
				
				boolean val1 = true;
				while (val1 == true) 
				{
					mr.displayMenu();
					int inp1 = s.nextInt();
					if (inp1 == 1) 
					{
						System.out.println("Enter item details");
						System.out.println("item name:");
						String name = s.next();
						System.out.println("item price:");
						int price = s.nextInt();
						System.out.println("item quantity:");
						int quantity = s.nextInt();
						System.out.println("item category:");
						String category = s.next();
						MercuryInc.AddItem(name,price,quantity,category,mr);
					}
					if (inp1 == 2) 
					{
						System.out.println("Choose item by code");
						MercuryInc.listItems();
						int itemcode = s.nextInt();
						item item = MercuryInc.getItem(itemcode);
						System.out.println("Enter edit details");
						System.out.println("Item price");
						int price = s.nextInt();
						System.out.println("Item quantity");
						int quantity = s.nextInt();
						item.price = price;
						item.quantity = quantity ;
						item.displayDetails();
					}
					if (inp1 == 3) 
					{
						System.out.println("Choose a category");
						ArrayList<String> arr = MercuryInc.getCategories();
						int c = s.nextInt();
						String cat = (String)arr.get(c-1);
						MercuryInc.search(cat);
					}
					if (inp1 == 4) 
					{
						System.out.println("Choose item by code");
						MercuryInc.listmerchantItems(mr);
						int itemcode = s.nextInt();
						item item = MercuryInc.getItem(itemcode);
						System.out.println("Choose offer");
						System.out.println("1) buy one get one");
						System.out.println("2) 25% off");
						int offer = s.nextInt();
						if (offer == 1) {
							item.offer = "buy one get one";
						}
						if (offer == 2) {
							item.offer = "25% off";
						}
						if (offer != 1 && offer != 2) {
							System.out.println("Invalid offer");
						}
						item.displayDetails();
					}
					if (inp1 == 5) 
					{	
						System.out.println(mr.getreward());
					}
					if (inp1 == 6) 
					{
						break;
					}
				}	
			}
			if (input == 2)
			{
				System.out.println("Choose Customer");
				System.out.println("1 Ali");
				System.out.println("2 Noobi");
				System.out.println("3 Bruno");
				System.out.println("4 Borat");
				System.out.println("5 Aladeen");
				int c = s.nextInt();
				customer cr = MercuryInc.getCustomer(c);
				
				boolean val1 = true;
				while (val1 == true) {
					cr.displayMenu();
					int inp1 = s.nextInt();
					if (inp1 == 1) 
					{
						System.out.println("Choose a category");
						ArrayList<String> arr = MercuryInc.getCategories();
						int m = s.nextInt();
						System.out.println("Choose item by code");
						String cat = (String)arr.get(m-1);
						MercuryInc.search(cat);
						System.out.println("Enter item code");
						int code = s.nextInt();
						item product = MercuryInc.getItem(code);
						System.out.println("Enter item quantity");
						int quantity = s.nextInt();
						if (product.quantity>=quantity) 
						{
							double cost=0;
							if (product.offer.matches("None"))
							{
								cost = product.price*quantity;
								
							}
							if (product.offer.matches("25% off"))
							{
								cost = product.price*quantity*(0.75);
							}
							if (product.offer.matches("buy one get one")) 
							{
								if (quantity%2 ==0) 
								{
									cost = product.price*quantity*(0.5);
								}
								if (quantity%2 ==1) {
									cost = product.price*(quantity-1)*(0.5) + product.price;
								}
							}
							System.out.println("Choose method of transaction");
							System.out.println("1) Buy item");
							System.out.println("2) Add item to cart");
							System.out.println("3) Exit");
							int trans = s.nextInt();
							if (trans == 1) {
								if ((cr.balance+cr.rewardbalance)>=cost*(1.005)) {
									product.merchant.contribution += cost*(0.005);
									cr.balance -= (cost+cost*(0.005));
									MercuryInc.addBalance(cost*0.01);
									product.merchant.rewardcounter += cost*(0.005);
									if (product.merchant.rewardcounter>=100) {
										product.merchant.rewardcounter -= 100;
										product.merchant.slots +=1;
										product.merchant.reward +=1 ;
									}
									cr.rewardcounter += 1;
									if (cr.rewardcounter == 5) {
										cr.rewardcounter = 0;
										cr.rewardbalance += 10;
										cr.reward += 10;
									}
									transaction t = new transaction(product.name,cost,quantity,product.merchant);
									cr.transactions.add(0,t);
									
									System.out.println("Item successfully brought");
								}
								else if ((cr.balance+cr.rewardbalance)<cost*(1.005)) {
									System.out.println("Insufficient balance");
								}
							}
							if (trans ==2) {
								transaction t = new transaction(product.name,cost,quantity,product.merchant);
								cr.cart.add(t);
							}
							if (trans != 1 && trans != 2 && trans !=3) {
								System.out.println("Invalid method of trasaction");
							}				
						}
						else if (product.quantity<quantity) {
							System.out.println("Insufficient quantity of the item available");
						}
						
					}
					if (inp1 == 2) 
					{
						
					}
					if (inp1 == 3) 
					{
						cr.getreward();
					}
					if (inp1 == 4) 
					{
						cr.prevtansactions();
					}
					if (inp1 == 5) 
					{
						break;
					}
					
				}
			}
			if (input == 3)
			{
				System.out.println("M/C");
				String p = s.next();
				int m = s.nextInt();
				if (p.matches("M")) 
				{
					MercuryInc.getMerchant(m).displayDetails();;
				}
				if (p.matches("C")) {
					MercuryInc.getCustomer(m).displayDetails();;
				}	
			}
			if (input == 4)
			{
				System.out.println(MercuryInc.getAccBalance());
			}
			if (input == 5)
			{
				val = false;
				break;
			}
		}
	}
}