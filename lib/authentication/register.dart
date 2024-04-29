import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:frontend/authentication/login.dart';
import 'package:frontend/utils/font_color.dart';
import 'package:frontend/views/home_page.dart';

class RegisterScreen extends StatefulWidget {
  const RegisterScreen({Key? key}) : super(key: key);

  @override
  State<RegisterScreen> createState() => _RegisterScreenState();
}

class _RegisterScreenState extends State<RegisterScreen> {
  TextEditingController _controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(const SystemUiOverlayStyle(
      statusBarColor: Color.fromARGB(255, 78, 64, 59), // Color for Android
      statusBarBrightness: Brightness.dark, // Brightness for iOS
    ));
    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          children: [
            const SizedBox(
              height: 60,
            ),
            Image.asset("assets/images/add.png", width: 200),
            const SizedBox(height: 20),
            const Align(
              alignment: Alignment.centerLeft,
              child: Padding(
                padding: EdgeInsets.only(left: 20.0),
                child:
                    Text("Enter your email", style: (AppTextStyles.mainColor)),
              ),
            ),
            const SizedBox(height: 6),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20.0),
              child: SizedBox(
                height: 50,
                child: TextField(
                  controller: _controller,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      borderSide:
                          BorderSide(color: Color.fromARGB(255, 83, 62, 54)),
                    ),
                    focusedBorder: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      borderSide: BorderSide(
                          color: Color.fromARGB(255, 83, 62,
                              54)), // Set the same color for focused border
                    ),
                  ),
                ),
              ),
            ),
            const SizedBox(
              height: 18,
            ),
            const Align(
              alignment: Alignment.centerLeft,
              child: Padding(
                padding: EdgeInsets.only(left: 20.0),
                child: Text(
                  "Enter your password",
                  style: AppTextStyles.mainColor,
                ),
              ),
            ),
            const SizedBox(height: 6),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20.0),
              child: SizedBox(
                height: 50,
                child: TextField(
                  controller: _controller,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      borderSide:
                          BorderSide(color: Color.fromARGB(255, 83, 62, 54)),
                    ),
                    focusedBorder: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      borderSide: BorderSide(
                          color: Color.fromARGB(255, 83, 62,
                              54)), // Set the same color for focused border
                    ),
                  ),
                ),
              ),
            ),
            const SizedBox(
              height: 18,
            ),
            const Align(
              alignment: Alignment.centerLeft,
              child: Padding(
                padding: EdgeInsets.only(left: 20.0),
                child: Text(
                  "Confirm your password",
                  style: AppTextStyles.mainColor,
                ),
              ),
            ),
            const SizedBox(height: 6),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20.0),
              child: SizedBox(
                height: 50,
                child: TextField(
                  controller: _controller,
                  decoration: const InputDecoration(
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      borderSide:
                          BorderSide(color: Color.fromARGB(255, 83, 62, 54)),
                    ),
                    focusedBorder: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      borderSide: BorderSide(
                          color: Color.fromARGB(255, 83, 62,
                              54)), // Set the same color for focused border
                    ),
                  ),
                ),
              ),
            ),
            const SizedBox(
              height: 10,
            ),
    
            Padding(
              padding: const EdgeInsets.all(20.0),
              child: SizedBox(
                width: double.infinity,
                height: 50,
                child: ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => const HomeScreen()),
                    );
                  },
                  style: ElevatedButton.styleFrom(
                    primary: const Color.fromARGB(255, 106, 85, 77), // Background color
                    onPrimary: Colors.white,
                    padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12), // Button padding
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8), // Button border radius
                    ),
                  ),
                  child: const Text(
                    'Register',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
              ),
            ),
            const SizedBox(
              height: 20,
            ),
            const Text("Already have an account?",style: AppTextStyles.mainColorBold,),
            const SizedBox(height: 10,),
            Padding(
              padding: const EdgeInsets.only(left: 20,right: 20,),
              child: SizedBox(
                width: double.infinity,
                height: 50,
                child: ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => const LoginScreen()),
                    );
                  },
                  style: ElevatedButton.styleFrom(
                    primary: const Color.fromARGB(255, 244, 225, 219), // Background color
                    onPrimary: const Color.fromARGB(255, 106, 85, 77),
                    padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12), // Button padding
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8), // Button border radius
                    ),
                  ),
                  child: const Text(
                    'Login',
                    style: TextStyle(fontSize: 16),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
