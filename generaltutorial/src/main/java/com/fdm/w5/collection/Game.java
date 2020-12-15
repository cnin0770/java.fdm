package com.fdm.w5.collection;


class Game implements Comparable<Game>{
// class Game{
	private String name;
	private double price;
	private int rating;
	private int id;
	
	public Game() {
		super();
	}
	
	public Game(String name, double price, int rating) {
		super();
		this.name = name;
		this.price = price;
		this.rating = rating;
		this.setId(this.hashCode());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + rating;
		return result;
	}
	
	/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (rating != other.rating)
			return false;
		return true;
	}
	*/
	
	@Override
	public String toString() {
		return "Game [name=" + name + ", price=" + price + ", rating=" + rating + "]";
	}

	public int compareTo(Game o) {
		if (this.rating != o.getRating()) return o.getRating() - this.rating;
		else if (this.price != o.getPrice()) return (int) ((this.price - o.getPrice()) *100); 
		else return this.getId() - o.getId();
	}
}
