package com.generic.setup;

public class brands {

	// Brands
	static String FG = "FG";
	static String GR = "GR";
	static String RY = "RY";
	static String GH = "GH";
	static String BD = "BD";

	// Envs
	static String Prod = "prod";

	// URLS
	static String FGURL = "frontgate.com";
	static String GRURL = "grandinroad.com";
	static String RYURL = "ryllace.com";
	static String GHURL = "garnethill.com";
	static String BDURL = "ballarddesigns.com";

	public static String get(String env, String brand) {
		String brandGetter = "https://";

		if (!env.contains(Prod))
			brandGetter += env + ".";
		else {
			brandGetter += "www.";
		}

		if (brand.contains(FG)) {
			brandGetter += FGURL;
		}
		if (brand.contains(GR)) {
			brandGetter += GRURL;
		}
		if (brand.contains(RY)) {
			brandGetter += RYURL;
		}
		if (brand.contains(GH)) {
			brandGetter += GHURL;
		}
		if (brand.contains(BD)) {
			brandGetter += BDURL;
		}

		return brandGetter;
	}

}
