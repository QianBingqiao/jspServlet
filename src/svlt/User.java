package svlt;
/**
 * 
* @ClassName: User 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author QianBingqiao 
* @date 2017年6月16日 下午4:29:48 
*
 */
public class User {
	String name;
	String age;
	String gander;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String age, String gander) {
		super();
		this.name = name;
		this.age = age;
		this.gander = gander;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGander() {
		return gander;
	}

	public void setGander(String gander) {
		this.gander = gander;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", gander=" + gander + "]";
	}
	

}
