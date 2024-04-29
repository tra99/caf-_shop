import 'package:flutter/material.dart';
import 'package:frontend/utils/coffee_tile.dart';
import 'package:frontend/utils/coffee_type.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {

  // list of coffee types
  final List coffeeType=[
    [
      'Cappucino',
      true
    ],
    [
      'Ice Latte',
      false
    ],
    [
      'Black Coffee',
      false
    ],
    [
      'Amazon Extra',
      false
    ]
  ];

  void coffeeTypeSelected(int index){
    setState(() {
      // this for loop makes every selection false
      for(int i=0;i<coffeeType.length;i++){
        coffeeType[i][1]=false;
      }
      coffeeType[index][1]=true;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: AppBar(
        elevation: 0,
        backgroundColor: Colors.transparent,
        leading: const Icon(Icons.menu),
        actions: const [
          Padding(
            padding: EdgeInsets.only(right: 25),
            child: Icon(Icons.person),
          )
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(items: const [
        BottomNavigationBarItem(
          icon: Icon(Icons.home),
          label: '',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.favorite),
          label: '',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.notifications),
          label: '',
        ),
      ]),
      body: Column(
        children: [
          const Padding(
            padding: EdgeInsets.symmetric(horizontal: 25.0),
            child: Text(
              "FIND THE BEST COFFEE FOR YOU",
              style: TextStyle(color: Colors.white, fontSize: 36),
            ),
          ),
          const SizedBox(
            height: 25,
          ),
          const Padding(
            padding: EdgeInsets.symmetric(horizontal: 25),
            child: TextField(
              decoration: InputDecoration(
                  prefixIcon: Icon(Icons.search),
                  hintText: 'Search your item',
                  focusedBorder: OutlineInputBorder(
                    borderSide: BorderSide(
                      color: Colors.grey,
                    ),
                  ),
                  enabledBorder: OutlineInputBorder(
                      borderSide: BorderSide(color: Colors.grey))),
            ),
          ),
          const SizedBox(
            height: 25,
          ),

          // horizontal listview of coffee types
          Container(
            height: 50,
            child: ListView.builder(
              scrollDirection: Axis.horizontal,
              itemCount: coffeeType.length,
              itemBuilder: (context, index) {
                return CoffeeType(
                  coffeeType: coffeeType[index][0], 
                  isSelected: coffeeType[index][1], 
                  onTab: (){
                    coffeeTypeSelected(index);
                  }
                );
              },
            ),
          ),

          // horizontal listview of coffee tiles
          Expanded(
              child: ListView(
            scrollDirection: Axis.horizontal,
            children: const[
              CoffeeTile(
                coffeeImagePath: 'assets/images/coffee.png',
                coffeeName: 'Latte',
                coffeePrice: "4.00",
                description: "With Almond Milk",
              ),
              CoffeeTile(
                coffeeImagePath: 'assets/images/blackCoffee.png',
                coffeeName: 'Cappucino',
                coffeePrice: "3.00",
                description: "With Avocado Tea",
              ),
              CoffeeTile(
                coffeeImagePath: 'assets/images/blackMilkTea.png',
                coffeeName: 'Black Milk Tea',
                coffeePrice: "2.80",
                description: "With Milk Chocolet",
              ),
              CoffeeTile(
                coffeeImagePath: 'assets/images/passion.png',
                coffeeName: 'Passion Cream',
                coffeePrice: "3.20",
                description: "With Butter Cream",
              ),
            ],
          ))
        ],
      ),
    );
  }
}
