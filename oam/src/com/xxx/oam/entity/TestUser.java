package com.xxx.oam.entity;

import com.jfinal.plugin.activerecord.Model;

public class TestUser extends Model<TestUser> {
	public static final TestUser dao = new TestUser().dao();
}
