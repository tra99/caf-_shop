import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:frontend/authentication/register.dart';
import 'package:frontend/utils/font_color.dart';
import 'package:frontend/views/home_page.dart';
import 'package:http/http.dart' as http;

class LoginScreen extends StatefulWidget {
  const LoginScreen({Key? key}) : super(key: key);

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {

  final TextEditingController _controller = TextEditingController();
  final TextEditingController _gmailController=TextEditingController();
  final TextEditingController _passwordController=TextEditingController();

  final formKey=GlobalKey<FormState>();
  String email="";
  String password="";
  String? errorMessage;

  Future<void> _login() async {
    if (!formKey.currentState!.validate()) {
      return; // Stop login if form is not valid
    }

    final String username = _gmailController.text;
    final String password = _passwordController.text;

    try {
      final response = await http.post(
        Uri.parse('${dotenv.env['BASE_URL']}/user/signin?username=$username&password=$password'),
      );


      if (response.statusCode == 200) {
        final responseData = response.body;

        if (responseData == "Sign-in successful") {
          Future.delayed(
            const Duration(milliseconds: 500),
            () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const HomeScreen()),
              );
            },
          );
        } else {
          setState(() {
            errorMessage = "Incorrect account";
          });
        }
      } else {
        setState(() {
          errorMessage = "An error occurred. Please try again later.";
        });
      }

    } catch (e) {
      setState(() {
        errorMessage = "An error occurred. Please try again later.";
      });
      print("Error: $e");
    }
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(const SystemUiOverlayStyle(
      statusBarColor: Color.fromARGB(255, 78, 64, 59), // Color for Android
      statusBarBrightness: Brightness.dark, // Brightness for iOS
    ));
    return Scaffold(
      body: SingleChildScrollView(
        child: Form(
          key: formKey,
          child: Column(
            children: [
              const SizedBox(
                height: 70,
              ),
              Image.asset("assets/images/add.png", width: 200),
              const SizedBox(height: 20),
              // const Text("Travell's Coffee"),
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
                  child: TextFormField(
                    controller: _gmailController,
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.all(Radius.circular(10)),
                        borderSide: BorderSide(color: Color.fromARGB(255, 83, 62, 54)),
                      ),
                      focusedBorder: OutlineInputBorder(
                        borderRadius: BorderRadius.all(Radius.circular(10)),
                        borderSide: BorderSide(
                          color: Color.fromARGB(255, 83, 62, 54)), // Set the same color for focused border
                      ),
                    ),
                    onChanged: (value) {
                      setState(() {
                        email = value;
                      });
                    },
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Email is required';
                      }
                      return RegExp(
                        r"^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\.[a-zA-Z]+"
                      ).hasMatch(value)
                          ? null
                          : 'Invalid email format';
                    },
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
                  child: TextFormField(
                    controller: _passwordController,
                    obscureText: true,
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.all(Radius.circular(10)),
                        borderSide:
                            BorderSide(color: Color.fromARGB(255, 83, 62, 54)),
                      ),
                      focusedBorder: OutlineInputBorder(
                        borderRadius: BorderRadius.all(Radius.circular(10)),
                        borderSide: BorderSide(
                            color: Color.fromARGB(255, 83, 62,54)), 
                      ),
                    ),
                    validator: (value) {
                      if(value!.length<6){
                        return "Password must have more than six characters";
                      }
                      return null;
                    },
                    onChanged: (value) {
                      setState(() {
                        password=value;
                      });
                    },
                  ),
                ),
              ),
              const SizedBox(
                height: 10,
              ),
              const Align(
                alignment: Alignment.centerRight,
                child: Padding(
                  padding: EdgeInsets.only(right: 20.0),
                  child: Text(
                    "Forgot your password?",
                    style: TextStyle(
                      color: Color.fromARGB(255, 106, 85, 77),
                      fontWeight: FontWeight.bold,
                      decoration: TextDecoration.underline,
                    ),
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.all(20.0),
                child: SizedBox(
                  width: double.infinity,
                  height: 50,
                  child: ElevatedButton(
                    onPressed: () {
                      if(formKey.currentState!.validate()){
                        // _login();
                        Navigator.push(context, MaterialPageRoute(builder: (context)=>const HomeScreen()));
                      }
                      // print("Hello");
                    },
                    style: ElevatedButton.styleFrom(
                      foregroundColor: Colors.white, backgroundColor: const Color.fromARGB(255, 106, 85, 77),
                      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12), 
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(8),
                      ),
                    ),
                    child: const Text(
                      'Login',
                      style: TextStyle(fontSize: 16),
                    ),
                  ),
                ),
              ),
              const SizedBox(
                height: 20,
              ),
              const Text("Don't have account yet?",style: AppTextStyles.mainColorBold,),
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
                        MaterialPageRoute(builder: (context) => const RegisterScreen()),
                      );
                    },
                    style: ElevatedButton.styleFrom(
                      foregroundColor: const Color.fromARGB(255, 106, 85, 77), backgroundColor: const Color.fromARGB(255, 244, 225, 219),
                      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(8), 
                      ),
                    ),
                    child: const Text(
                      'Register',
                      style: TextStyle(fontSize: 16),
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
