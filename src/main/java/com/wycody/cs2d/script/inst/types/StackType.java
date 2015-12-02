package com.wycody.cs2d.script.inst.types;

public enum StackType {
    INT('I', "int"),
    OBJECT('O', "String"),
    LONG('L', "long");
    private final char id;
    private String genericType;
    
    private StackType(char id, String genericType){
        this.id = id;
        this.genericType = genericType;
    }
    
    /**
     * Fake StackType for no stack operations.
     * Use this or null.
     */
    public static final StackType[] NONE = new StackType[0];
    
    /**
     * Formats an array of stack types for debugging purposes.
     * @param types
     * @return 
     */
    public static String format(StackType[] types){
        if(types == null || types.length == 0) {
			return "V";
		}
        String r = "";
        for(StackType t : types) {
			r += t == null ? "V" : t.id;
		}
        return r;
    }

	/**
	 * @return the genericType
	 */
	public String getGenericType() {
		return genericType;
	}

}
