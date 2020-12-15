# shellbin
Please first source _project/init.sh_ script to set up your local env.
for example:
```bash
    $ source project/init.sh
```
this script provides the following function:
- export project root path.
- create necessary log files and directory.
- provide constants for the project.
- for testing dir and files, please call "setupTest" after sourcing.
- "recycle" and "restore" can be used under project root and project dir.
    - if need more scope, please consider soft link. examples are made below.

===== NOTE ===== 
Be sure sourcing init script every time with a new opened terminal, 
as it does not survive the end of each session. 

Please consider include this file in your shell profile to avoid 
repeating sourcing.

    by Chuan.Ning@FDMGroup

## todo
- [ ] reverse check list order in project/recycle
