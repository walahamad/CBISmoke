package com.generic.setup;

public class cselector extends SelTestCase {

	public String DesktopSelector = "";
	public String TabletSelector = "";
	public String MobileSelector = "";

	public cselector(String Desktop, String Tablet, String Mobile) {
		this.DesktopSelector = Desktop;
		this.TabletSelector = Tablet;
		this.MobileSelector = Mobile;
	}

	public cselector(String Desktop, String Mobile) {
		this.DesktopSelector = Desktop;
		this.TabletSelector = Desktop;
		this.MobileSelector = Mobile;
	}

	public cselector(String Desktop) {
		this.DesktopSelector = Desktop;
		this.TabletSelector = Desktop;
		this.MobileSelector = Desktop;
	}

	public String get() {
		String browserType = getBrowserName();
		if (browserType.contains("iPhone")) {
			return MobileSelector;
		}
		if (browserType.contains("iPad")) {
			return TabletSelector;
		}
		return DesktopSelector;

	}

}
