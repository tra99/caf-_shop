import 'package:flutter/material.dart';

class CoffeeTile extends StatelessWidget {

  final String coffeeImagePath;
  final String coffeeName;
  final String coffeePrice;
  final String description;

  const CoffeeTile({
    super.key,
    required this.coffeeImagePath,
    required this.coffeeName,
    required this.coffeePrice,
    required this.description,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(left: 25, bottom: 25),
      child: Container(
        width: 200,
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(12),
          color: Colors.black54,
        ),
        child: Padding(
          padding: const EdgeInsets.all(12),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              ClipRRect(
                borderRadius: BorderRadius.circular(12),
                child: Image.asset(
                  coffeeImagePath,
                  fit: BoxFit.cover,
                ),
              ),
              Padding(
                padding: const EdgeInsets.symmetric(
                  vertical: 12,
                  horizontal: 8,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      coffeeName,
                      style: const TextStyle(fontSize: 20),
                    ),
                    const SizedBox(height: 6,),
                    Text(
                      description,
                      style: TextStyle(color: Colors.grey[700]),
                    ),
                    const SizedBox(
                      height: 10,
                    ),
                    Padding(
                      padding: const EdgeInsets.symmetric(
                        // horizontal: 4,
                      ),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text('\$ '+ coffeePrice),
                          Container(
                            padding: const EdgeInsets.all(4),
                            decoration: BoxDecoration(
                              color: Colors.orange,
                              borderRadius: BorderRadius.circular(6),
                            ),
                            child: const Icon(Icons.add),
                          ),
                        ],
                      ),
                    )
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
