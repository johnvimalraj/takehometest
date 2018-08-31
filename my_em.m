%{
EM Algorithm
-----------------
AUTH: JOHN VIMALRAJ
DATE: 30 AUG 2018
-----------------
Given data : old_faithful.dat

%}


%STEP 1 Load data

data = dlmread('old_faithful.dat', '\t', 26, 0);

n =length(data);
% 272 from the file.


%% STEP 2 Initialize µk, Σk, and πk. k =2 for bivariants calculated using
%k-means
mu1 = sum(data)/n % ML estimate n length(data)
mu2 = mean(data) % Matlab function
V = zeros(3, n);
for i = 1:n
V(:,i) = data(i,:) - mean(data);
end
sigma1 = V*V'./n % ML estimate
sigma2 = V*V'./(n-1) % unbiased estimate
lambda = cov(data)
d = norm(mu1 - mu1) + norm(mu2 - mu2);

shift = 10000;  % a big number
iter = 0;       % counter
epsilon = 0.001; % percision
formatSpec = 'iteration: %d, error: %2.4f, mu1: [%2.4f %2.4f], mu2: [%2.4f %2.4f] \n';

while shift > epsilon
    iter = iter + 1;

%{
STEP 3 E STEP
Expectation 
%}
for ii = 1: size(data,1)
    x = data(ii, 1:2);
    p_1 = prob(x, mu1, sigma1, lambda(1,1));
    p_2 = prob(x, mu2, sigma2, lambda(1,2));
    
    if p_1 > p_2
        data(ii, 3) = 1;
    else
        data(ii, 3) = 2;
    end
end

%{
STEP 4 M STEP
Maximization
%}

points_1 = data(data(:,2) == 1,:);
points_2 = data(data(:,2) == 2,:);

points_3 = size(points_1,1) / size(data,1);
points_4 = 1 - points_1;

% calculate the weights
lambda = [points_1, points_2];


% calculate the means
mu1 = [mean(points_3(:,1)), mean(points_3(:,2))];
mu2 = [mean(points_4(:,1)), mean(points_4(:,2))];

% calculate the variances
sigma1 = [std(points_3(:,1)) 0; 0 std(points_3(:,2))];
sigma2 = [std(points_4(:,1)) 0; 0 std(points_4(:,2))];

end

%% plot the results
f = mvnpdf([X1(:) X2(:)], mu1, sigma1);
contour(X1, X2, reshape(f, 100, 100))
xlabel('X1'); ylabel('X2'); colorbar
hold on; scatter(data(:,1), data(:,2)); axis square
