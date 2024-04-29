import 'package:flutter/material.dart';
import 'package:frontend/authentication/login.dart';
import 'package:frontend/views/home_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(brightness: Brightness.light),
      
      home: const LoginScreen(),
    );
  }
}
