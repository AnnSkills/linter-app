import jslint from "./jslint.mjs";
import fs from "fs";
(async function () {
  let result;
  let source = "function foo() {console.log(\u0027hello world\u0027);}\n";

  result = jslint.jslint(source);
  result = jslint.jslint_report(result);

  await fs.promises.mkdir(".artifact/", {recursive: true});
  await fs.promises.writeFile(".artifact/sourse_id", result);
}());
