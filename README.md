# HideMyKeys
Android tutorial on how to hide API keys when uploading to Github.

This is a tutorial I created for How to hide API keys in Android when uploading to Github. It uses part's of Cassio Oliveira 's 
"A strategy to secure your API keys using Gradle" found at 
https://medium.com/@cassioso/a-strategy-to-secure-your-api-keys-using-gradle-b9c107272860

My tutorial shows how to secure your API keys in 2 different ways : 
1) Using your Gradle file.
2) Using your res/values folder.

First way: Using your Gradle file.
Step 1) Go to your Project file structure on the left hand side of Android and right click on your Application folder.
In this case it will be the 'HideMyKeys' folder. Click New -> File. Name the file "keystore.properties".
In this file you will store your api keys, in my example I store 2 API keys, one int and one String so you can see how.
Step 2) Go to your Grade file inside your "app" folder and right before your android section (as I did in this app) paste this code:

def keystorePropertiesFile = rootProject.file("keystore.properties")
def keystoreProperties = new Properties()
keystoreProperties.load(new FileInputStream(keystorePropertiesFile))

Step 3) This is where you put your keys into your BuildConfig file. You must use the same name you gave your API keys in keystore.properties.
My two keys were MY_STRING_API_KEY and MY_INT_API_KEY, the names I give them below is the names I use when I access them in my application.
In the same Grade file from Step 2, right after "buildTypes"
go ahead and add this code:

buildTypes.each {
        it.buildConfigField 'String', 'API_STRING', keystoreProperties['MY_STRING_API_KEY']
        it.buildConfigField 'int', 'API_INT', keystoreProperties['MY_INT_API_KEY']
    }


Step 4) Sync your gradle files.
Step 5) Now you can access them in your application. As you can see in my example in MainActivity I access them with
 String myStringApiKey = BuildConfig.API_STRING;
 int myIntApiKey = BuildConfig.API_INT;
 
 It is important to remember that when typing "BuildConfig" you DO NOT import anything Lint suggests into your app even though Lint will
 give you suggestions. If you import any of the BuildConfigs suggested you will not be able to find your keys. Just type it out.
 
 
 
 Second way: Using your res/values folder.
 I included this second option in the case you might have to obscure an API key in your AndroidManifest file, and while you could
 use the method above to accomplish that task it is a bit more difficult, and this second option to me is a simpler way to go.
 
 Step 1) Go to your res/values folder, right click on it and select new -> Values resource file. Name it "api_keys".
 Here is where you'll create String resources of your API key just like you would a String resource in your strings.xml file.
 
 In my example I did:
 <string name="my_api_string">123abc456def</string>
 
 Step 2) Now you access this in an Activity, as I did in MainActivity with:
 String mySecondApiString = getString(R.string.my_api_string);
 OR
 In your AndroidManifest file like so:
 <meta-data
            android:name="io.company.ApiKey"
            android:value="@string/my_api_string" />
            

The last step to securing your keys before uploading to Github is to add your "keystore.properties" or "api_keys.xml" (or both) to the
high level .gitignore file found in your Project structure (not the one in your "app" folder). The easiest way to add these files
is with the .gitignore plugin tool found in Android. I did not include this in my project since then you would not be able to see
the example files.

All the examples I used in this README file can be found in my Application.

Hope you found this helpful. Enjoy.
