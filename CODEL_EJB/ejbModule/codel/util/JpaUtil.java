package codel.util;

public class JpaUtil {
	public static enum CONTACT_TYPE{
		CONTACT {
			public String toString(){
				return "Contact";
			}
		},
		ENTERPRISE {
			public String toString(){
				return "Enterprise";
			}
		}
	}
}
