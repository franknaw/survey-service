#!/bin/bash

package=$0

while test $# -gt 0; do
    case "$1" in
        -h|--help)
            echo "$package - Output check-in comments for this Git repo."
            echo " "
            echo "$package [options]"
            echo " "
            echo "options:"
            echo "-h, --help"
            echo "-o, --output-file=FILE"
            echo "-n, --name=PACKAGER'S USERNAME"
            echo "-e, --email=PACKAGER'S EMAIL ADDRESS"
            echo "-v, --version=VERSION NUMBER"
            echo "-r, --revision=REVISION NUMBER"
            exit 0
            ;;
        -o)
            shift;
            if [[ $# -gt 0 ]]; then
                export OUTPUT=$1
                shift
            else
                "No output file specified."
                exit 1
            fi
            ;;
        --output-file*)
            export OUTPUT=`echo $1 | sed -e 's/^[^=]*=//g'`
            shift
            ;;
        -n)
            shift;
            if [[ $# -gt 0 ]]; then
                export NAME=$1
                shift
            fi
            ;;
        --name*)
            export NAME=`echo $1 | sed -e 's/^[^=]*=//g'`
            shift
            ;;
        -e)
            shift;
            if [[ $# -gt 0 ]]; then
                export EMAIL=$1
                shift
            fi
            ;;
        --email*)
            export EMAIL=`echo $1 | sed -e 's/^[^=]*=//g'`
            shift
            ;;
        -v)
            shift;
            if [[ $# -gt 0 ]]; then
                export VERSION=$1
                shift
            fi
            ;;
        --version*)
            export VERSION=`echo $1 | sed -e 's/^[^=]*=//g'`
            shift
            ;;
        -r)
            shift;
            if [[ $# -gt 0 ]]; then
                export REVISION=$1
                shift
            fi
            ;;
        --revision*)
            export REVISION=`echo $1 | sed -e 's/^[^=]*=//g'`
            shift
            ;;
        *)
            break
            ;;
    esac
done

# Make sure we have all the values we need.
if [[ -z $VERSION ]]; then
    >&2 echo "No version number specified.  Use either -v or --version.  Exiting.";
    exit 1;
fi

if [[ -z $REVISION ]]; then
    >&2 echo "No revision specified.  Use either -r or --revision.  Exiting.";
    exit 1;
fi

if [[ -z $NAME ]]; then
    export NAME=`whoami`
    >&2 echo "No packager's name specified.  Defaulting to $NAME."
fi

if [[ -z $EMAIL ]]; then
    export EMAIL="$NAME@novetta.com"
    >&2 echo "Packager's email address not specified.  Defaulting to $EMAIL."
fi


if [[ -n $OUTPUT ]]; then
    exec > $OUTPUT
fi

export NOW=$(date +"%a %b %d %Y")

# Each release will have a tag in Git.  Group comments by tag.
git for-each-ref --sort='-*committerdate' --format='%(refname:short)' refs/tags | grep -v '^$' | while read TAG ; do

    if [[ $NEXT_TAG ]]; then
        echo
        STR=`git log -1 --pretty="format:%aD|%cn|%ce" $NEXT_TAG | perl -n -e'/(.*?),\s+(.*?)\s+(.*?)\s+(\d+).*?\|(.*?)\|(.*)/ && print "$1 $3 $2 $4 - $5 <$6>\n"'`

        echo "* $STR - $NEXT_TAG"
    else
        echo "* $NOW - $NAME <$EMAIL> - $VERSION-$REVISION"
    fi

    GIT_PAGER=cat git log --no-merges --format=" - %s" $TAG..$NEXT_TAG
    NEXT_TAG=$TAG
done

FIRST_TAG=$(git tag -l | head -1)
echo
STR=`git log -1 --pretty="format:%aD|%cn|%ce" $FIRST_TAG | perl -n -e'/(.*?),\s+(.*?)\s+(.*?)\s+(\d+).*?\|(.*?)\|(.*)/ && print "$1 $3 $2 $4 - $5 <$6>\n"'`

if [[ -z $FIRST_TAG ]]; then
    echo "* $STR - $VERSION-$REVISION"
else
    echo "* $STR - $FIRST_TAG"
fi

GIT_PAGER=cat git log --no-merges --format=" - %s" $FIRST_TAG
