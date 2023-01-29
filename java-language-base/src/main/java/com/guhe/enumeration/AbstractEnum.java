package com.guhe.enumeration;

/**
 * @author njl
 * @date 2023/1/29
 */
public enum AbstractEnum {
	ENUM1 {
		@Override
		public void far() {
			System.out.println(this.name());
		}
	}, ENUM2 {
		@Override
		public void far() {
			System.out.println(this.name());
		}
	}, ENUM3 {
		@Override
		public void far() {
			System.out.println(this.name());
		}
	};

	AbstractEnum() {
	}

	public void bar() {
		this.far();
	}

	public abstract void far();
}
