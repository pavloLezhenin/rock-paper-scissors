java -jar d:/Projects/Bucharest/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate -Dapis -Dmodels^
  -i controllers.yaml ^
  -l spring ^
  -o ../../.. ^
  --template-dir templates/codegen ^
  --api-package com.games.rps.controller ^
  --model-package com.games.rps.dto ^
  --invoker-package com.games.rps ^
  --model-name-suffix DTO ^
  -v