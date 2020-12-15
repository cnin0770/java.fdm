#!/bin/bash

<<'README'
Please first source this script to set up your local env.
for example:
    $ source init.sh
this script provides the following function:
- export project root path.
- create necessary log files and directory.
- provide constants for the project.
- for testing dir and files, please call "setupTest" after sourcing.
- "recycle" and "restore" can be used under project root and project dir.
    - if need more scope, please consider soft link. examples are made below.

===== NOTE ===== 
Be sure sourcing this script every time with a new terminal, 
as it does not survive the end of each session. 

Please consider include this file in your shell profile to avoid 
repeating sourcing.

    by Chuan.Ning@FDMGroup
README

PRODUCTION_SHELLBIN=true

if $PRODUCTION_SHELLBIN ; then
    export SHELLBIN_ROOT_PATH="$HOME/"
    project_path="$SHELLBIN_ROOT_PATH/project"
else 
    project_path="$(pwd -P)"
    export SHELLBIN_ROOT_PATH="$(dirname $project_path)"
fi

error_log="$project_path/errors.log"
bin_path="$SHELLBIN_ROOT_PATH/recyclebin"
restore_info_name='.restore.info'
restore_info_path="$SHELLBIN_ROOT_PATH/$restore_info_name"

protected_files=('restore' 'recycle' 'init.sh' "$restore_info_name")

ln -s "$project_path/recycle" "$SHELLBIN_ROOT_PATH/recycle" 2> $error_log
ln -s "$project_path/restore" "$SHELLBIN_ROOT_PATH/restore" 2> $error_log

function setupShellBin() {
    if [ ! -f "$bin_path/.bin" ]; then
        mkdir -p $bin_path
        echo '# this file preserves the recyclebin directory.' > $bin_path/.bin
        echo "dir $bin_path made"
    fi

    if [ ! -f $error_log ]; then
        echo '# this file stores error log.' > $error_log
        echo "file $error_log made"
    fi

    if [ ! -f $restore_info_path ]; then
        echo '# this file stores recycle information.' > $restore_info_path
        echo "file $restore_info_path made"
    fi
}

function setupTest(){
    mkdir -p "$SHELLBIN_ROOT_PATH/testdir/innerdir"
    mkdir -p "$SHELLBIN_ROOT_PATH/testdir/innerdirplus"
    echo 'this is test' > "$SHELLBIN_ROOT_PATH/testdir/innerdir/testfilea"
    echo 'this is test' > "$SHELLBIN_ROOT_PATH/testdir/testfileb"
    echo 'this is test' > "$SHELLBIN_ROOT_PATH/testdir/testfilec"
}

### MAIN ###
setupShellBin