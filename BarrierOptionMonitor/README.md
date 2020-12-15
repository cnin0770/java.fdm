# BarrierOptionMonitor

## Project Description

## Development Updates
- 04/27 project initiated

## Technology stacks
- Spring Security
- JPA
- Rest Data API
- Vue JS Integrated with Thymeleaf
- Task Scheduling

## Manual of Git branches
1. check all your current branches, remote and local.
```git
$ git branch -a
```

2. checkout and switch to remote branch.
```git 
$ git checkout --track origin/your_remote_branch
```

> this creates a local branch in the same name that tracking the remote branch.

3. checkout local branch.
```git 
$ git branch your_local_branch
```

> this duplicates a new branch based on your current branch.

4. local merge.
```git
$ git checkout master
$ git merge your_branch
```

> first checkout to the branch to be merging, say `master`.
> then merge `your_branch` into `master`. after merge, `master` will be updated, while `your_branch` not (already up-to-date).

5. remove a branch.
```git 
$ git branch -d your_branch
```

6. local commits need not to be pushed. you might merge locally before pushing to remote.

7. lastly, push your updated `master` to remote.