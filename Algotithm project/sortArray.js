// using count sort with JavaScript, pasted two test cases for [5,2,3,1] and [5,1,1,2,0,0] with a run time of 187ms 

/**
 * @param {number[]} nums
 * @return {number[]}
 */
var sortArray = function(nums) {
    let countingSort = () => {
        let counts = {};
    let minVal = Math.min (...nums);
    let maxVal = Math.max (...nums);

    nums.forEach(val => {
        if ( counts[val] == undefined) {
             counts[val] = 0;
        }
        counts[val] += 1
    });

    let index = 0;

    for (let val = minVal; val <= maxVal; val +=1) {
        while (counts[val] > 0) {
            nums[index] = val;
            index += 1;
            counts[val] -= 1;
        }
    }
    }

    countingSort(nums);
    return nums;    
};