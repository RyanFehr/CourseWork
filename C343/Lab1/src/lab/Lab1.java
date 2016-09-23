package lab;

import java.util.Arrays;
import java.util.List;

public class Lab1 {

	Double averageRainfall(String data)
	{
		Double totalRainfall = 0.0;
		int totalRecords = 0;
		Double currentFall;
		List<String> rainfalls = Arrays.asList(data.split("\\s"));
		for(String item : rainfalls)
		{
			currentFall = Double.parseDouble(item);
			if(currentFall < 0)
			{
				if(currentFall != -999)
				{
					//this item is stepped over
				}
				else
				{
					if(totalRecords>0)
					{
						return totalRainfall/totalRecords;
					}
					else
					{
						return  0.0;
					}
				}
			}
			else
			{
				totalRainfall += currentFall;
				totalRecords++;
			}
		}
		if(totalRecords>0)
		{
			return totalRainfall/totalRecords;
		}
		else
		{
			return 0.0;
		}
	}
	
	public static void main(String[] args) 
	{
		String input1 = "2 45 -65 45 87 -999 23 34 89 ";
		String input2 = "-999 45 -65 45 87 23 34 89 ";
		String input3 = "1 1 1 1 -999 23 34 89 ";
		Lab1 test = new Lab1();
		System.out.println(test.averageRainfall(input1));
		System.out.println(test.averageRainfall(input2));
		System.out.println(test.averageRainfall(input3));
	}

}