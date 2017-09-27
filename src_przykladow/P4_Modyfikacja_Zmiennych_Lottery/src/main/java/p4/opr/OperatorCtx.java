package p4.opr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class OperatorCtx {
	
	private String name;
	private Collection<Integer> groups;

	public static OperatorCtx getCtx() {
		return new OperatorCtx("Darek", new ArrayList<>(Arrays.asList(300, 400)));
	}

	public OperatorCtx(String name, Collection<Integer> groups) {
		super();
		this.name = name;
		this.groups = groups;
	}
	
	public String getName() {
		return name;
	}

	public Collection<Integer> getGroups() {
		return groups;
	}

}

