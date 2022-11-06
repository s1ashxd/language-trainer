[![wakatime](https://wakatime.com/badge/user/7bd5c752-a5ea-4366-aa1b-b104cdd271eb/project/5af4704c-fdb9-4d46-84ed-e41bceb9085e.svg)](https://wakatime.com/badge/user/7bd5c752-a5ea-4366-aa1b-b104cdd271eb/project/5af4704c-fdb9-4d46-84ed-e41bceb9085e)
# Russian Language Trainer
A simple application written in Kotlin using the libGDX framework. The game is intended for Russian-speaking students of the 2nd grade.<br>
Простое приложение, написанное на языке Kotlin с использованием фреймворка libGDX. Игра предназначена для русскоговорящих учеников 2-ых классов.

# Installation and launch
1. Download Java Runtime Enviroment (min 1.8)
2. Download ```language-trainer.jar``` from Releases
3. Run with JRE or via command line: ```java -jar language-trainer.jar```

# Environment
- Config path folder: ```%user%/.prefs/language-trainer.xml```
- [Rule template](assets/rules/rule-0.json)<br>
  The rule file must be named rule-{index}.json<br>
  After adding the rule file, increment the [RULE_COUNT constant](core/src/ru/wyvern/trainer/screens/RuleChoiceScreen.kt)
  
# Contributors
`s1ashxd` - wrote the code<br>
`7x7x49` - drew the assets<br>
`rianix` - idea generator, described the rules
